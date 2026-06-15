package com.entawaspada.cuaca;

import com.google.gson.annotations.SerializedName;

public class RootModel {

    @SerializedName("list")
    private List<ListModel> listModelList;

    @SerializedName("city")
    private CityModel cityModel;

    public RootModel() { }

    public List<ListModel> getListModelList() { return listModelList; }

    public void setListModelList(List<ListModel> listModelList) {
        this.listModelList = listModelList;
    }

    public CityModel getCityModel() { return cityModel; }

    public void setCityModel(CityModel cityModel) { this.cityModel = cityModel; }

}