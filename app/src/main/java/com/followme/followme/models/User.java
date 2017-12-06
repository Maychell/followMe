package com.followme.followme.models;

/**
 * Created by maychellfernandesdeoliveira on 27/11/2017.
 */

public class User {
    private String name;
    private String email;
    private String imagePath;

    User(String name, String email, String imagePath) {
        this.name = name;
        this.email = email;
        this.imagePath = imagePath;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
