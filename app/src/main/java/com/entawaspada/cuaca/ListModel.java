package com.entawaspada.cuaca;


import com.google.gson.annotations.SerializedName;

public class ListModel {

    @SerializedName("main")
    private MainModel mainModel;

    @SerializedName("weather")
    private List<WeatherModel> weatherModelList;

    private String dt_txt;

    public ListModel() { }

    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public List<WeatherModel> getWeatherModelList() {
        return weatherModelList;
    }

    public void setWeatherModelList(List<WeatherModel> weatherModelList) {
        this.weatherModelList = weatherModelList;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

}