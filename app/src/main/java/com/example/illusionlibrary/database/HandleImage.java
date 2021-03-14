package com.example.illusionlibrary.database;

import com.example.illusionlibrary.models.Image;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HandleImage {
    private static DatabaseReference mDatabase;

    public static void writeNewImage(String id, String name, String link) {
        Image image = new Image(name, link);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("image").child(id).setValue(image);
    }
}
