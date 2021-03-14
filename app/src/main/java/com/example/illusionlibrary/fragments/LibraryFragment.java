package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    private static final String TAG = "LibraryFragment";
    private List<Image> images = new ArrayList<>();
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

        getAllImages();
        // Set up adapter & recycler view
        rvLibrary = fragmentLibraryBinding.rvLibrary;
        adapter = new ImageAdapter(getActivity(), images);
        rvLibrary.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvLibrary.setAdapter(adapter);

        return fragmentLibraryBinding.getRoot();
    }

    public void getAllImages() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("image").child("1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public synchronized void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Map<String,String> td=(HashMap<String, String>)task.getResult().getValue();
                    Image image = new Image(td.get("imageName"),td.get("imageLink"));
                    images.add(image);
                    adapter.notifyDataSetChanged();
                    Log.e(TAG, String.valueOf(images.size()));
                }
            }
        });
    }
}