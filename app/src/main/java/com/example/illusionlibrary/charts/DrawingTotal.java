package com.example.illusionlibrary.charts;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.illusionlibrary.R;
import com.example.illusionlibrary.models.Image;
import com.example.illusionlibrary.models.Response;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawingTotal {

    private static final String TAG = "PieChart";

    public static void drawTotalStats(PieChart chart, final Context context, Image image) {
        Log.e(TAG, String.valueOf(Response.responses.get(image.imageId).size()));
        HashMap<String, Integer> data = classify(Response.responses.get(image.imageId));

        List<PieEntry> entries = new ArrayList<>();
        for(String type:data.keySet()){
            entries.add(new PieEntry(data.get(type).floatValue() / Response.responses.get(image.imageId).size() * 100, type));
        }

        PieDataSet pieDataSet = new PieDataSet(entries,"Answer Distribution");
        pieDataSet.setValueTextSize(12f);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);

        chart.setData(pieData);
        chart.invalidate();
    }

    private static HashMap<String, Integer> classify(List<Response> input) {
        HashMap<String, Integer> result = new HashMap<>();
        for (int i = 0; i < input.size(); ++i) {
            Response response = input.get(i);
            if (result.get(response.content) == null) {
                result.put(response.content, 0);
            } else {
                result.put(response.content, result.get(response.content) + 1);
            }
        }
        return result;
    }
}
