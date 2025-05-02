package com.example.safespace;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ChipGroup tabChipGroup;
    private RecyclerView recyclerForumPosts;
    private ForumPostAdapter forumPostAdapter;
    private List<ForumPost> forumPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();
        setupTabChips();
        setupBottomNavigation();
        setupRecyclerView();
        loadForumPosts();
    }

    private void initializeViews() {
        tabChipGroup = findViewById(R.id.tab_chip_group);
        recyclerForumPosts = findViewById(R.id.recycler_forum_posts);
    }

    private void setupTabChips() {
        // Set General tab as selected by default
        Chip generalChip = findViewById(R.id.chip_general);
        generalChip.setChecked(true);

        tabChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Handle tab selection and filter posts accordingly
            filterPostsByCategory(checkedId);
        });
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
    }

    private void setupRecyclerView() {
        recyclerForumPosts.setLayoutManager(new LinearLayoutManager(this));
        forumPosts = new ArrayList<>();
        forumPostAdapter = new ForumPostAdapter(this, forumPosts, getSupportFragmentManager());

        recyclerForumPosts.setAdapter(forumPostAdapter);
    }

    private void loadForumPosts() {
        // Add sample forum posts based on the screenshots
        forumPosts.clear();

        // Define all numeric values as final variables first
        final int likes = 125;
        final int comments = 15;
        final int shares = 155;

        // First post from Golangirya
        List<String> tags1 = new ArrayList<>(Arrays.asList("General", "Safety"));
        ForumPost post1 = new ForumPost(
                "Golangirya",
                "8 min ago",
                "Is there any Girls Hostel available in Bandra?",
                "I need a PG in Bandra as I work till late night and it gets really dark and I feel unsafe?",
                null,
                tags1,
                likes, comments, shares);
        forumPosts.add(post1);

        // Second post from Sunaina
        List<String> tags2 = new ArrayList<>(Arrays.asList("General", "Support"));
        ForumPost post2 = new ForumPost(
                "Sunaina",
                "2 min ago",
                "Product Query",
                "What is the use of this white button?",
                R.drawable.pink_blue_product,
                tags2,
                likes, comments, shares);
        forumPosts.add(post2);

        // Add duplicate post to match the screenshot
        List<String> tags3 = new ArrayList<>(Arrays.asList("General", "Support"));
        ForumPost post3 = new ForumPost(
                "Sunaina",
                "3 min ago",
                "Product Query",
                "What is the use of this white button?",
                R.drawable.pink_blue_product,
                tags3,
                likes, comments, shares);
        forumPosts.add(post3);

        forumPostAdapter.notifyDataSetChanged();
    }

    private void filterPostsByCategory(int tabId) {
        // In a real app, you would filter the posts based on tab selection
        // For this demo, we'll just reload all posts
        loadForumPosts();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle bottom navigation selection
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Already on home
            return true;
        } else if (id == R.id.nav_search) {
            // Handle search tab
            return true;
        } else if (id == R.id.nav_twitter) {
            // Handle twitter tab
            return true;
        } else if (id == R.id.nav_games) {
            // Handle games tab
            return true;
        } else if (id == R.id.nav_profile) {
            // Handle profile tab
            return true;
        }
        return false;
    }
}