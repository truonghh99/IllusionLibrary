package com.example.illusionlibrary.models;

public class Image {
    public String imageName;
    public String imageLink;

    public Image() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Image(String name, String link) {
        this.imageName = name;
        this.imageLink = link;
    }
}
