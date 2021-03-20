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
        Log.e(TAG, String.valueOf(Response.responses.get(image.imageId).size()));
    }
}
