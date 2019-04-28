package com.example.epicentre;

import com.google.gson.annotations.SerializedName;

public class ResponseJSON {
    @SerializedName("msg")
    String location;

    public ResponseJSON(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}

