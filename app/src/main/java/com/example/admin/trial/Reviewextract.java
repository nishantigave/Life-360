package com.example.admin.trial;

/**
 * Created by Admin on 31/03/2018.
 */

public class Reviewextract {
    String username, review;

    public Reviewextract(String username, String review) {
        this.username = username;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
