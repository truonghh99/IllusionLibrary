package com.example.illusionlibrary.charts;

import android.content.Context;
import android.util.Log;

import com.example.illusionlibrary.models.Image;
import com.example.illusionlibrary.models.Response;
import com.github.mikephil.charting.charts.PieChart;

public class Drawing {

    private static final String TAG = "PieChart";

    public static void drawTotalStats(PieChart chart, Context context, Image image) {
        Log.e(TAG, String.valueOf(Response.responses.get(image.imageId).size()));
    }
}
