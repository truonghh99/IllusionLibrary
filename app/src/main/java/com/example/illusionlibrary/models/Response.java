package com.example.illusionlibrary.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Response {

    private static final String TAG = "Response";
    public static HashMap<String, List<Response>> responses = new HashMap<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    public String imageId;
    public String content;
    public int age;
    public String occupation;
    public String gender;
    public String ethnicity;
    public String nationality;
    public int eyesight;
    public String eyeCondition;
    public String dominantHand;
    public int trained;

    public Response(String imageId, String content, int age, String occupation, String gender, String ethnicity, String nationality, int eyesight, String eyeCondition, String dominantHand, int trained) {
        this.imageId = imageId;
        this.content = content;
        this.age = age;
        this.occupation = occupation;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.nationality = nationality;
        this.eyesight = eyesight;
        this.eyeCondition = eyeCondition;
        this.dominantHand = dominantHand;
        this.trained = trained;
    }

    public Response() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public void saveToDatabase(final String imageId, final String content) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = ref.child("users").child(user.getUid());

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.e(TAG, user.toString());
                age = user.getAge();
                occupation = user.getOccupation();
                gender = user.getGender();
                ethnicity = user.getEthnicity();
                nationality = user.getNationality();
                eyesight = user.getEyesight();
                eyeCondition = user.getEyeCondition();
                dominantHand = user.getDominantHand();
                trained = user.getTrained();
                DatabaseReference responseRef = ref.child("responses").push();
                Response response = new Response(imageId, content, age, occupation, gender, ethnicity, nationality,
                                                    eyesight, eyeCondition, dominantHand, trained);
                responseRef.setValue(response);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };

        usersRef.addValueEventListener(userListener);
    }
}
