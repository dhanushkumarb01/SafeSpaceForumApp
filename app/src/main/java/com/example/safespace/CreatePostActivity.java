package com.example.safespace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePostActivity extends AppCompatActivity {

    private EditText etSubject, etContent;
    private TextView tagGeneral, tagSupport, tagLegal, tagSafety, tagProblems, tagFeatures;
    private Button btnCreatePost, btnCancel;
    private ImageButton btnBack;

    private String selectedTag = ""; // To store which tag is selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // Initialize views
        etSubject = findViewById(R.id.etSubject);
        etContent = findViewById(R.id.etContent);

        tagGeneral = findViewById(R.id.tagGeneral);
        tagSupport = findViewById(R.id.tagSupport);
        tagLegal = findViewById(R.id.tagLegal);
        tagSafety = findViewById(R.id.tagSafety);
        tagProblems = findViewById(R.id.tagProblems);
        tagFeatures = findViewById(R.id.tagFeatures);

        btnCreatePost = findViewById(R.id.btnCreatePost);
        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.btnBack);

        // Set up tag selection listeners
        setUpTagListeners();

        // Set up button click listeners
        setUpButtonListeners();
    }

    private void setUpTagListeners() {
        // Create a common click listener for all tags
        View.OnClickListener tagClickListener = view -> {
            // Reset all tags to default state
            resetTagsSelection();

            // Highlight the selected tag
            view.setAlpha(1.0f);
            selectedTag = ((TextView) view).getText().toString();
            Toast.makeText(this, "Selected tag: " + selectedTag, Toast.LENGTH_SHORT).show();
        };

        // Apply the listener to all tag views
        tagGeneral.setOnClickListener(tagClickListener);
        tagSupport.setOnClickListener(tagClickListener);
        tagLegal.setOnClickListener(tagClickListener);
        tagSafety.setOnClickListener(tagClickListener);
        tagProblems.setOnClickListener(tagClickListener);
        tagFeatures.setOnClickListener(tagClickListener);
    }

    private void resetTagsSelection() {
        // Make all tags semi-transparent
        tagGeneral.setAlpha(0.7f);
        tagSupport.setAlpha(0.7f);
        tagLegal.setAlpha(0.7f);
        tagSafety.setAlpha(0.7f);
        tagProblems.setAlpha(0.7f);
        tagFeatures.setAlpha(0.7f);
    }

    private void setUpButtonListeners() {
        btnCreatePost.setOnClickListener(view -> {
            if (validateInput()) {
                createNewPost();
            }
        });

        btnCancel.setOnClickListener(view -> {
            // Just finish the activity, returning to previous screen
            finish();
        });

        btnBack.setOnClickListener(view -> {
            // Same as cancel
            finish();
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (etSubject.getText().toString().trim().isEmpty()) {
            etSubject.setError("Subject cannot be empty");
            isValid = false;
        }

        if (etContent.getText().toString().trim().isEmpty()) {
            etContent.setError("Content cannot be empty");
            isValid = false;
        }

        if (selectedTag.isEmpty()) {
            Toast.makeText(this, "Please select a tag", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    private void createNewPost() {
        // Get the input values
        String subject = etSubject.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        // Create a new ForumPost object
        ForumPost post = new ForumPost();
        post.setSubject(subject);
        post.setContent(content);
        post.setTag(selectedTag);
        post.setUsername("Golangnya"); // This would typically come from the logged-in user's profile
        post.setTimestamp(System.currentTimeMillis());

        // Add the post to your database or API
        // For example:
        // yourDatabase.addPost(post);

        // Show a success message
        Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show();

        // Navigate back to the forum or main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Bottom navigation handler methods
    public void onHomeClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onSearchClick(View view) {
        // TODO: Navigate to search screen
    }

    public void onTwitterClick(View view) {
        // TODO: Navigate to twitter integration screen
    }

    public void onGamesClick(View view) {
        // TODO: Navigate to games screen
    }

    public void onProfileClick(View view) {
        // TODO: Navigate to profile screen
    }
}