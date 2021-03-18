package com.example.illusionlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.illusionlibrary.adapters.ImageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image implements Parcelable {
    public String imageId;
    public String imageName;
    public String imageLink;

    public Image() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Image(String id, String name, String link) {
        this.imageId = id;
        this.imageName = name;
        this.imageLink = link;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
