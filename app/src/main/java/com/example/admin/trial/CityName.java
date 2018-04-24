package com.example.admin.trial;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Admin on 13/03/2018.
 */

public class CityName {
    Double latitude,longitude;
    int impact;

    public Double getLatitude() {
        return latitude;
    }

    public CityName(Double latitude, Double longitude, int impact) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.impact = impact;
    }
    public CityName() {

    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LatLng getCity(){
        LatLng city = new LatLng(latitude,longitude);
        return city;
    }
    public int getImpact() {
        return impact*13000;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }
}
