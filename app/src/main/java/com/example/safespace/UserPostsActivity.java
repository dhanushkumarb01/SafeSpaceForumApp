package com.example.safespace;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class UserPostsActivity extends AppCompatActivity {

    private RecyclerView postsRecyclerView;
    private UserPostsAdapter adapter;
    private List<UserPost> userPosts;

    // Category chips
    private Chip chipGeneral, chipSupport, chipLegal, chipSafety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        // Initialize views
        postsRecyclerView = findViewById(R.id.postsRecyclerView);

        chipGeneral = findViewById(R.id.chipGeneral);
        chipSupport = findViewById(R.id.chipSupport);
        chipLegal = findViewById(R.id.chipLegal);
        chipSafety = findViewById(R.id.chipSafety);

        ImageButton backButton = findViewById(R.id.backButton);
        ImageButton editProfileButton = findViewById(R.id.editProfile);
        ImageButton cameraButton = findViewById(R.id.cameraButton);

        // Set up click listeners
        backButton.setOnClickListener(v -> onBackPressed());

        editProfileButton.setOnClickListener(v -> {
            // Navigate to profile editing screen
            Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(this, EditProfileActivity.class);
            // startActivity(intent);
        });

        cameraButton.setOnClickListener(v -> {
            // Open camera or gallery
            Toast.makeText(this, "Camera clicked", Toast.LENGTH_SHORT).show();
        });

        // Set up category chip listeners
        setupCategoryChips();

        // Initialize sample data
        initializeSamplePosts();

        // Set up RecyclerView
        setupRecyclerView();
    }

    private void setupCategoryChips() {
        // Reset all chips to unselected style
        View.OnClickListener chipClickListener = v -> {
            resetChips();

            Chip clickedChip = (Chip) v;
            clickedChip.setChipBackgroundColorResource(R.color.dark_blue);
            clickedChip.setTextColor(getResources().getColor(android.R.color.white));

            // Filter posts based on selected category
            filterPostsByCategory(clickedChip.getText().toString());
        };

        chipGeneral.setOnClickListener(chipClickListener);
        chipSupport.setOnClickListener(chipClickListener);
        chipLegal.setOnClickListener(chipClickListener);
        chipSafety.setOnClickListener(chipClickListener);
    }

    private void resetChips() {
        chipGeneral.setChipBackgroundColorResource(android.R.color.white);
        chipGeneral.setTextColor(getResources().getColor(android.R.color.black));

        chipSupport.setChipBackgroundColorResource(android.R.color.white);
        chipSupport.setTextColor(getResources().getColor(android.R.color.black));

        chipLegal.setChipBackgroundColorResource(android.R.color.white);
        chipLegal.setTextColor(getResources().getColor(android.R.color.black));

        chipSafety.setChipBackgroundColorResource(android.R.color.white);
        chipSafety.setTextColor(getResources().getColor(android.R.color.black));
    }

    private void filterPostsByCategory(String category) {
        List<UserPost> filteredPosts = new ArrayList<>();

        // If "General" is selected, show all posts
        if (category.equals("General")) {
            adapter.updatePosts(userPosts);
            return;
        }

        // Filter posts by category
        for (UserPost post : userPosts) {
            if (post.getCategory().equals(category)) {
                filteredPosts.add(post);
            }
        }

        adapter.updatePosts(filteredPosts);
    }

    private void initializeSamplePosts() {
        userPosts = new ArrayList<>();

        // Create sample posts that match the ones in the UI screenshot
        userPosts.add(new UserPost(
                1,
                "Golangirya",
                "Is there any Girls Hostel available in Bandra? I need a PG in Bandra as I work till late night and it gets really dark and I feel unsafe?",
                "3 mins ago",
                125,
                15,
                155,
                "Safety"
        ));

        userPosts.add(new UserPost(
                2,
                "Golangirya",
                "My friend is facing some issues in her college and no actions have been taken. If anyone can help her legally, Please Connect",
                "3 days ago",
                125,
                15,
                155,
                "Legal"
        ));

        userPosts.add(new UserPost(
                3,
                "Golangirya",
                "My friend is facing some issues in her college and no actions have been taken. If anyone can help her legally, Please Connect",
                "4 days ago",
                125,
                15,
                155,
                "Legal"
        ));
    }

    private void setupRecyclerView() {
        adapter = new UserPostsAdapter(userPosts,
                // Delete Post Click Listener
                post -> showDeleteConfirmationDialog(post),
                // Edit Post Click Listener
                post -> {
                    // Navigate to edit post screen
                    Toast.makeText(this, "Edit post: " + post.getId(), Toast.LENGTH_SHORT).show();
                    // Intent intent = new Intent(this, EditPostActivity.class);
                    // intent.putExtra("post_id", post.getId());
                    // startActivity(intent);
                }
        );

        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsRecyclerView.setAdapter(adapter);
    }

    private void showDeleteConfirmationDialog(UserPost post) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_delete_confirmation);
        dialog.setCancelable(true);

        Button cancelButton = dialog.findViewById(R.id.cancelButton);
        Button confirmDeleteButton = dialog.findViewById(R.id.confirmDeleteButton);

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        confirmDeleteButton.setOnClickListener(v -> {
            // Delete the post
            deletePost(post);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void deletePost(UserPost post) {
        // In a real app, you would delete from database or make an API call
        userPosts.remove(post);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Post deleted successfully", Toast.LENGTH_SHORT).show();

        // Refresh the filtered view if a category is selected
        String selectedCategory = getSelectedCategory();
        if (!selectedCategory.equals("General")) {
            filterPostsByCategory(selectedCategory);
        }
    }

    private String getSelectedCategory() {
        if (chipGeneral.getTextColors().getDefaultColor() == getResources().getColor(android.R.color.white)) {
            return "General";
        } else if (chipSupport.getTextColors().getDefaultColor() == getResources().getColor(android.R.color.white)) {
            return "Support";
        } else if (chipLegal.getTextColors().getDefaultColor() == getResources().getColor(android.R.color.white)) {
            return "Legal";
        } else if (chipSafety.getTextColors().getDefaultColor() == getResources().getColor(android.R.color.white)) {
            return "Safety";
        }

        return "General"; // Default
    }
}