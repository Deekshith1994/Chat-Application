package com.example.kdeek.enelchat;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kdeek on 11/22/2016.
 */
public class User implements Parcelable{

    String fistName;
    String lastName;
    String fullName;
    String profilePicUrl;
    String gender;
    String email;
    String password;
    String userID;

    protected User(Parcel in) {
        fistName = in.readString();
        lastName = in.readString();
        fullName = in.readString();
        profilePicUrl = in.readString();
        gender = in.readString();
        email = in.readString();
        password = in.readString();
        userID = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getID() {
        return userID;
    }

    public void setID(String ID) {
        this.userID = ID;
    }

    public User() {
    }

    public User(String fistName, String gender, String profilePicUrl, String fullName, String lastName, String email, String password) {
        this.fistName = fistName;
        this.gender = gender;
        this.profilePicUrl = profilePicUrl;
        this.fullName = fullName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fistName);
        parcel.writeString(lastName);
        parcel.writeString(fullName);
        parcel.writeString(profilePicUrl);
        parcel.writeString(gender);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(userID);
    }
}