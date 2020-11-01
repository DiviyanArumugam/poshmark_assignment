package com.poshmark.assignment.model.input;

import com.google.gson.annotations.SerializedName;

public class ResourceCostInfo {


    @SerializedName("large")
    Float large;

    @SerializedName("xlarge")
    Float xLarge;

    @SerializedName("2xlarge")
    Float x2Large;

    @SerializedName("4xlarge")
    Float x4Large;

    @SerializedName("8xlarge")
    Float x8Large;

    @SerializedName("10xlarge")
    Float x10Large;

    public Float getLarge() {
        return large;
    }

    public void setLarge(Float large) {
        this.large = large;
    }

    public Float getxLarge() {
        return xLarge;
    }

    public void setxLarge(Float xLarge) {
        this.xLarge = xLarge;
    }

    public Float getX2Large() {
        return x2Large;
    }

    public void setX2Large(Float x2Large) {
        this.x2Large = x2Large;
    }

    public Float getX4Large() {
        return x4Large;
    }

    public void setX4Large(Float x4Large) {
        this.x4Large = x4Large;
    }

    public Float getX8Large() {
        return x8Large;
    }

    public void setX8Large(Float x8Large) {
        this.x8Large = x8Large;
    }

    public Float getX10Large() {
        return x10Large;
    }

    public void setX10Large(Float x10Large) {
        this.x10Large = x10Large;
    }

    public Float getCost(String serverType) {
        switch (serverType) {
            case "large":
                return getLarge();
            case "xlarge":
                return getxLarge();
            case "2xlarge":
                return getX2Large();
            case "4xlarge":
                return getX4Large();
            case "8xlarge":
                return getX8Large();
            case "10xlarge":
                return getX10Large();
            default:
                return null;
        }
    }
}
