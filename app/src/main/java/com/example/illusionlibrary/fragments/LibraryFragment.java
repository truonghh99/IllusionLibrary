package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.illusionlibrary.R;
import com.example.illusionlibrary.adapters.ImageAdapter;
import com.example.illusionlibrary.databinding.FragmentLibraryBinding;
import com.example.illusionlibrary.models.Image;
import com.example.illusionlibrary.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryFragment extends Fragment {

    private static final String TAG = "LibraryFragment";
    private List<Image> images = new ArrayList<>();
    private RecyclerView rvLibrary;
    private ImageAdapter adapter;
    private int size = 8;

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

        getAllImages();
        // Set up adapter & recycler view
        rvLibrary = fragmentLibraryBinding.rvLibrary;
        adapter = new ImageAdapter(getActivity(), images);
        rvLibrary.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLibrary.setAdapter(adapter);

        return fragmentLibraryBinding.getRoot();
    }

    public void getAllImages() {
        for (int i = 1; i <= size; ++i) {
            String id = Integer.toString(i);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("image").child(id);
            ValueEventListener imageListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Image image = dataSnapshot.getValue(Image.class);
                    images.add(image);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                }
            };
            ref.addValueEventListener(imageListener);
        }
    }
}