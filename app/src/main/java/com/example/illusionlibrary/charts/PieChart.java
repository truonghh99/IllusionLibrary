package com.example.illusionlibrary.charts;

import android.content.Context;
import android.util.Log;

import com.example.illusionlibrary.models.Image;
import com.example.illusionlibrary.models.Response;

import java.util.List;

public class PieChart {

    private static final String TAG = "PieChart";

    public static void drawTotalStats(Context context, Image image) {
        List<Response> responses = image.getResponses();
        Log.e(TAG, Integer.toString(responses.size()));
    }
}
