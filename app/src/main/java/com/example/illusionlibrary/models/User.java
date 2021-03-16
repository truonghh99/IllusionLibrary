package com.example.illusionlibrary.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    private String uid;
    private String email;
    private String name;
    private int age;
    private String occupation;
    private String gender;
    private String ethnicity;
    private String nationality;
    private int eyesight;
    private String eyeCondition;
    private String dominantHand;
    private int trained;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void saveToDatabase() {
        DatabaseReference usersRef = ref.child("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        usersRef.child(uid).setValue(this);
    }

}
