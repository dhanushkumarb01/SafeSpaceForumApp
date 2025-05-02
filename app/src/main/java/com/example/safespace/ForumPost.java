package com.example.safespace;

import java.io.Serializable;
import java.util.List;

public class ForumPost implements Serializable {
    private String username;
    private String timePosted;
    private String title;
    private String content;
    private Integer imageResource;
    private List<String> categories;
    private int likeCount;
    private int commentCount;
    private int shareCount;

    public ForumPost(String username, String timePosted, String title, String content,
                     Integer imageResource, List<String> categories,
                     int likeCount, int commentCount, int shareCount) {
        this.username = username;
        this.timePosted = timePosted;
        this.title = title;
        this.content = content;
        this.imageResource = imageResource;
        this.categories = categories;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTimePosted() { return timePosted; }
    public void setTimePosted(String timePosted) { this.timePosted = timePosted; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Integer getImageResource() { return imageResource; }
    public void setImageResource(Integer imageResource) { this.imageResource = imageResource; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }

    public int getLikes() { return likeCount; }
    public void setLikes(int likeCount) { this.likeCount = likeCount; }

    public int getComments() { return commentCount; }
    public void setComments(int commentCount) { this.commentCount = commentCount; }

    public int getShares() { return shareCount; }
    public void setShares(int shareCount) { this.shareCount = shareCount; }

    // Optional: getId() if needed for CommentActivity
    public String getId() {
        // Dummy ID based on timestamp or username; customize if needed
        return username + "_" + timePosted;
    }
}
