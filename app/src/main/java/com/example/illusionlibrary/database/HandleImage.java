package com.example.illusionlibrary.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.illusionlibrary.models.Image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HandleImage {
    private static final String TAG = "HandleImage";
    private static DatabaseReference mDatabase;

    public static void writeNewImage(String id, String name, String link) {
        Image image = new Image(name, link);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("image").child(id).setValue(image);
    }

}
