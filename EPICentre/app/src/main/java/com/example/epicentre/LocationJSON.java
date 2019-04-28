package com.example.epicentre;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LocationJSON implements Parcelable {

    @SerializedName("lat")
    double latitude;
    @SerializedName("lng")
    double longitude;


    public LocationJSON(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected LocationJSON(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static final Creator<LocationJSON> CREATOR = new Creator<LocationJSON>() {
        @Override
        public LocationJSON createFromParcel(Parcel in) {
            return new LocationJSON(in);
        }

        @Override
        public LocationJSON[] newArray(int size) {
            return new LocationJSON[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
