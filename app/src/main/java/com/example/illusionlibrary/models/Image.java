package com.example.illusionlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.illusionlibrary.adapters.ImageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image implements Parcelable {
    private static final String TAG = "Image";
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

    protected Image(Parcel in) {
        imageId = in.readString();
        imageName = in.readString();
        imageLink = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public static void saveNewImage(String link, String question) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("image").push();
        String id = ref.getKey();
        Image image = new Image(id, link, question);
        ref.setValue(image);
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
        parcel.writeString(imageId);
        parcel.writeString(imageName);
        parcel.writeString(imageLink);
    }

    public void getAllResponses() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("server/saving-data/fireblog").child("responses");
        Query query = ref.orderByChild("imageId").equalTo(imageId);
        ChildEventListener responseListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Response response = snapshot.getValue(Response.class);
                Response.responses.get(imageId).add(response);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Response response = snapshot.getValue(Response.class);
                Response.responses.get(imageId).add(response);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        query.addChildEventListener(responseListener);
    }
}
