package com.entawaspada.cuaca;

import com.google.gson.annotations.SerializedName;

public class CityModel {

    private long sunrise, sunset;
    private String name;

    @SerializedName("coord")
    private CoordModel coordModel;

    public CityModel() {
    }

    public long getSunrise() { return sunrise; }

    public void setSunrise(long sunrise) { this.sunrise = sunrise; }

    public long getSunset() { return sunset; }

    public void setSunset(long sunset) { this.sunset = sunset; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public CoordModel getCoordModel() { return coordModel; }

    public void setCoordModel(CoordModel coordModel) { this.coordModel = coordModel; }

}