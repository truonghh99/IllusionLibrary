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

public class Response {

    private static final String TAG = "Response";
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getEyesight() {
        return eyesight;
    }

    public void setEyesight(int eyesight) {
        this.eyesight = eyesight;
    }

    public String getEyeCondition() {
        return eyeCondition;
    }

    public void setEyeCondition(String eyeCondition) {
        this.eyeCondition = eyeCondition;
    }

    public String getDominantHand() {
        return dominantHand;
    }

    public void setDominantHand(String dominantHand) {
        this.dominantHand = dominantHand;
    }

    public int getTrained() {
        return trained;
    }

    public void setTrained(int trained) {
        this.trained = trained;
    }

    public Response() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void saveToDatabase() {
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
