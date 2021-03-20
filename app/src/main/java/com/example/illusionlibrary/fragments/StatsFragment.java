package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.illusionlibrary.R;
import com.example.illusionlibrary.charts.Drawing;
import com.example.illusionlibrary.databinding.FragmentStatsBinding;
import com.example.illusionlibrary.models.Image;
import com.github.mikephil.charting.charts.PieChart;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    private static final String IMAGE_KEY = "image";

    private Image image;
    private TextView tvTitle;
    private FragmentStatsBinding fragmentStatsBinding;
    private PieChart pcTotal;

    public StatsFragment() {
        // Required empty public constructor
    }

    public static StatsFragment newInstance(Image image) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putParcelable(IMAGE_KEY, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getParcelable(IMAGE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentStatsBinding = FragmentStatsBinding.inflate(getLayoutInflater());
        tvTitle = fragmentStatsBinding.tvTitle;
        pcTotal = fragmentStatsBinding.pcTotal;
        tvTitle.setText(image.getImageName());
        Drawing.drawTotalStats(pcTotal, getContext(), image);
        return fragmentStatsBinding.getRoot();
    }
}