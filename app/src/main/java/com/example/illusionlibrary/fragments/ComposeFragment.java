package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.illusionlibrary.R;
import com.example.illusionlibrary.databinding.FragmentComposeBinding;
import com.example.illusionlibrary.models.Image;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComposeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComposeFragment extends Fragment {

    private ImageView ivPreview;
    private EditText etLink;
    private EditText etQuestion;
    private Button btPreview;
    private Button btSubmit;
    private String link;
    private String question;

    public ComposeFragment() {
        // Required empty public constructor
    }

    public static ComposeFragment newInstance() {
        ComposeFragment fragment = new ComposeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentComposeBinding fragmentComposeBinding = FragmentComposeBinding.inflate(getLayoutInflater());
        etLink = fragmentComposeBinding.etLink;
        etQuestion = fragmentComposeBinding.etQuestion;
        btPreview = fragmentComposeBinding.btnPreview;
        btSubmit = fragmentComposeBinding.btnSubmit;
        ivPreview = fragmentComposeBinding.ivPreview;

        btPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link = etLink.getText().toString();
                Glide.with(getContext())
                        .load(link.replaceAll("http:", "https:"))
                        .override(1000, 500)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                        .into(ivPreview);
                etLink.getText().clear();
                btPreview.setVisibility(View.GONE);
                etLink.setHint("Your link has been saved");
                etQuestion.requestFocus();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question = etQuestion.getText().toString();
                Image.saveNewImage(link, question);
                Toast.makeText(getContext(), "Your image has been uploaded!", Toast.LENGTH_SHORT).show();
            }
        });

        return fragmentComposeBinding.getRoot();
    }
}