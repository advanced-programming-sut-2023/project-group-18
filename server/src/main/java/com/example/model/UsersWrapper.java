package com.example.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
public class UsersWrapper {
    private ArrayList<User> userArrayList;

    public UsersWrapper() {
    }
    public UsersWrapper(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }
}
