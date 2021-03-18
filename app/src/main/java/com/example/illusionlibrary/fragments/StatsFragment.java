package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.illusionlibrary.R;
import com.example.illusionlibrary.databinding.FragmentStatsBinding;
import com.example.illusionlibrary.models.Image;

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
        tvTitle.setText(image.getImageName());
        return fragmentStatsBinding.getRoot();
    }
}