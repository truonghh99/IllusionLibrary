package com.example.illusionlibrary.charts;

import android.content.Context;
import android.graphics.Color;
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
        int size = Response.responses.get(image.imageId).size();
        Log.e(TAG, String.valueOf(size));
        HashMap<String, Float> data = classify(Response.responses.get(image.imageId),size);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));

        List<PieEntry> entries = new ArrayList<>();
        for(String content:data.keySet()){
            entries.add(new PieEntry(data.get(content), content));
        }

        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setValueTextSize(14f);
        PieData pieData = new PieData(pieDataSet);
        pieDataSet.setColors(colors);
        pieData.setDrawValues(true);

        chart.setData(pieData);
        chart.invalidate();
    }

    private static HashMap<String, Float> classify(List<Response> input, int size) {
        HashMap<String, Float> result = new HashMap<>();
        for (int i = 0; i < input.size(); ++i) {
            Response response = input.get(i);
            if (result.get(response.content) == null) {
                result.put(response.content, (float) 1);
            } else {
                result.put(response.content, result.get(response.content) + 1);
            }
        }
        for(String content:result.keySet()){
            float percentage = result.get(content);
            Log.e(TAG, content + ": " + result.get(content) + " - " + percentage);
            result.put(content, percentage);
        }
        return result;
    }
}
