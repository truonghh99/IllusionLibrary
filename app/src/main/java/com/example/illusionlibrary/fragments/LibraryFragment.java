package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.illusionlibrary.R;
import com.example.illusionlibrary.adapters.ImageAdapter;
import com.example.illusionlibrary.databinding.FragmentLibraryBinding;
import com.example.illusionlibrary.models.Image;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    private List<Image> images;
    private RecyclerView rvLibrary;
    private ImageAdapter adapter;
    public LibraryFragment() {
    }

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentLibraryBinding fragmentLibraryBinding = FragmentLibraryBinding.inflate(getLayoutInflater());

        Image.getAllImages();
        Log.d("COUNT", String.valueOf(Image.images.size()));
        // Set up adapter & recycler view
        rvLibrary = fragmentLibraryBinding.rvLibrary;
        adapter = new ImageAdapter(getActivity(), Image.images);
        rvLibrary.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvLibrary.setAdapter(adapter);

        return fragmentLibraryBinding.getRoot();
    }
}