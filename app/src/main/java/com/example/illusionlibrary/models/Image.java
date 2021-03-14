package com.example.illusionlibrary.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image {
    public String imageName;
    public String imageLink;
    public static List<Image> images = new ArrayList<>();


    public Image() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Image(String name, String link) {
        this.imageName = name;
        this.imageLink = link;
    }

    public static void getAllImages() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("image").child("1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Map<String,String> td=(HashMap<String, String>)task.getResult().getValue();
                    Image image = new Image(td.get("imageName"),td.get("imageLink"));
                    images.add(image);
                }
            }
        });
        for (int i = 0; i < 100000; ++i);
    }

    public String getImageName() {
        return imageName;
    }
}
