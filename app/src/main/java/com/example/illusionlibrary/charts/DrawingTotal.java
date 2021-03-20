package com.example.illusionlibrary.charts;

import android.content.Context;
import android.util.Log;

import com.example.illusionlibrary.models.Image;
import com.example.illusionlibrary.models.Response;
import com.github.mikephil.charting.charts.PieChart;

import java.util.HashMap;
import java.util.List;

public class DrawingTotal {

    private static final String TAG = "PieChart";

    public static void drawTotalStats(PieChart chart, Context context, Image image) {
        Log.e(TAG, String.valueOf(Response.responses.get(image.imageId).size()));
        HashMap<String, Integer> data = classify(Response.responses.get(image.imageId));

    }

    private static HashMap<String, Integer> classify(List<Response> input) {
        HashMap<String, Integer> result = new HashMap<>();
        for (int i = 0; i < input.size(); ++i) {
            Response response = input.get(i);
            result.put(response.content, result.get(response.content) + 1);
        }
        return result;
    }
}
