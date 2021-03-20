package com.example.illusionlibrary.charts;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.illusionlibrary.models.Image;
import com.example.illusionlibrary.models.Response;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class PieChart {

    private static final String TAG = "PieChart";

    public static void drawTotalStats(Context context, Image image) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("server/saving-data/fireblog").child("responses");
        Query query = ref.orderByChild("imageId").equalTo(image.imageId);
        final List<Response> responses = new ArrayList<>();
        ChildEventListener responseListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Response response = snapshot.getValue(Response.class);
                responses.add(response);
                Log.e(TAG, String.valueOf(responses.size()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Response response = snapshot.getValue(Response.class);
                responses.add(response);
                Log.e(TAG, String.valueOf(responses.size()));
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
