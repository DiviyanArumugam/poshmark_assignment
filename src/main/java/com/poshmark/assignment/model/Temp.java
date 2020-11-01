package com.poshmark.assignment.model;

import static com.poshmark.assignment.constants.Constants.*;

public class Temp {

    String region;

    String serverType;

    Integer cpus;

    Float cost;

    Float cpuByCost;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public Integer getCpus() {
        return cpus;
    }

    public void setCpus(Integer cpus) {
        this.cpus = cpus;
        updateCpuByCost();
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
        updateCpuByCost();
    }

    public void updateCpuByCost() {
        if (cpus != null && cost != null) {
            setCpuByCost(cpus/cost);
        }
    }

    public Float getCpuByCost() {
        return cpuByCost;
    }

    public void setCpuByCost(Float cpuByCost) {
        this.cpuByCost = cpuByCost;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "region='" + region + '\'' +
                ", serverType='" + serverType + '\'' +
                ", cpus=" + cpus +
                ", cost=" + cost +
                ", cpuByCost=" + cpuByCost +
                '}';
    }

    public int compareCost(Temp o2) {
        if (cost < o2.cost) {
                return 1;
        } else if (cost.equals(o2.cost)) {
            if (serverToCpuMap.get(serverType) > serverToCpuMap.get(o2.serverType)) {
                return 1;
            } else if (serverToCpuMap.get(serverType).equals(serverToCpuMap.get(o2.serverType))) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
