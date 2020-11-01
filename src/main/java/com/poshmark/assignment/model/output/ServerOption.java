package com.poshmark.assignment.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.poshmark.assignment.util.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerOption {

    @Expose
    @SerializedName("region")
    String region;

    @Exclude
    float totalCost;

    @Expose
    @SerializedName("total_cost")
    String totalCostStr;

    @Expose
    @SerializedName("servers")
    List<String> serverList = new ArrayList<>();

    @Exclude
    Map<String, Integer> serverMap = new HashMap<>();

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public Map<String, Integer> getServerMap() {
        return serverMap;
    }

    public void setServerMap(Map<String, Integer> serverMap) {
        this.serverMap = serverMap;
    }

    public String getTotalCostStr() {
        return totalCostStr;
    }

    public void setTotalCostStr(String totalCostStr) {
        this.totalCostStr = totalCostStr;
    }

    public List<String> getServerList() {
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }
}
