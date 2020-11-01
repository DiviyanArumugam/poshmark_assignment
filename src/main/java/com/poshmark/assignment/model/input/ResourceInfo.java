package com.poshmark.assignment.model.input;

import com.google.gson.annotations.SerializedName;

public class ResourceInfo {

    @SerializedName("us-east")
    ResourceCostInfo usEast;

    @SerializedName("us-west")
    ResourceCostInfo usWest;

    @SerializedName("asia")
    ResourceCostInfo asia;

    public ResourceCostInfo getUsEast() {
        return usEast;
    }

    public void setUsEast(ResourceCostInfo usEast) {
        this.usEast = usEast;
    }

    public ResourceCostInfo getUsWest() {
        return usWest;
    }

    public void setUsWest(ResourceCostInfo usWest) {
        this.usWest = usWest;
    }

    public ResourceCostInfo getAsia() {
        return asia;
    }

    public void setAsia(ResourceCostInfo asia) {
        this.asia = asia;
    }
}
