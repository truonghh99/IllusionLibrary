package com.example.illusionlibrary.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.illusionlibrary.R;
import com.example.illusionlibrary.databinding.FragmentProfileBinding;
import com.example.illusionlibrary.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding fragmentProfileBinding;
    private User currUser = new User();
    private EditText etName;
    private EditText etDob;
    private EditText etOccupation;
    private EditText etGender;
    private EditText etEthnicity;
    private EditText etNationality;
    private EditText etEyesight;
    private EditText etEyeCondition;
    private EditText etDominantHand;
    private EditText etTrained;
    private Button btnSave;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = FragmentProfileBinding.inflate(getLayoutInflater());

        etName = fragmentProfileBinding.etName;
        etDob = fragmentProfileBinding.etDob;
        etOccupation = fragmentProfileBinding.etOccupation;
        etGender = fragmentProfileBinding.etGender;
        etEthnicity = fragmentProfileBinding.etEthnicity;
        etNationality = fragmentProfileBinding.etNationality;
        etEyesight = fragmentProfileBinding.etEyesight;
        etEyeCondition = fragmentProfileBinding.etEyeCondition;
        etDominantHand = fragmentProfileBinding.etDominantHand;
        etTrained = fragmentProfileBinding.etTrained;
        btnSave = fragmentProfileBinding.btnSave;

        getInfo();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currUser.setName(etName.getText().toString());
                currUser.setAge(convertAge(etDob.getText().toString()));
                currUser.setOccupation(etOccupation.getText().toString());
                currUser.setGender(etGender.getText().toString());
                currUser.setEthnicity(etEthnicity.getText().toString());
                currUser.setNationality(etNationality.getText().toString());
                currUser.setEyesight(Integer.parseInt(etEyesight.getText().toString()));
                currUser.setEyeCondition(etEyeCondition.getText().toString());
                currUser.setDominantHand(etDominantHand.getText().toString());
                currUser.setTrained(Integer.parseInt(etTrained.getText().toString()));

                currUser.saveToDatabase();
                Toast.makeText(getContext(), "Your information has been updated!", Toast.LENGTH_SHORT).show();
            }
        });
        return fragmentProfileBinding.getRoot();
    }

    private int convertAge(String dob) {
        dob = dob.substring(6);
        int year = Integer.parseInt(dob);
        return 2021 - year;
    }

    public void getInfo() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog");
        DatabaseReference usersRef = ref.child("users").child(currUser.getUid());
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.e("HAHA", user.toString());
                etName.setText(user.getName());
                etDob.setText("06/09/1999");
                etOccupation.setText(user.getOccupation());
                etGender.setText(user.getGender());
                etEthnicity.setText(user.getEthnicity());
                etNationality.setText(user.getNationality());
                etEyesight.setText(Integer.toString(user.getEyesight()));
                etEyeCondition.setText(user.getEyeCondition());
                etDominantHand.setText(user.getDominantHand());
                etTrained.setText(Integer.toString(user.getTrained()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        usersRef.addValueEventListener(postListener);
    }
}