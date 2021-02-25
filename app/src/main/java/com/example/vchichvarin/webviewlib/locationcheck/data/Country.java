package com.example.vchichvarin.webviewlib.locationcheck.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("country_name")
    @Expose
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
