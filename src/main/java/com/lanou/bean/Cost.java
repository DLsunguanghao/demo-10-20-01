package com.lanou.bean;

import java.util.Date;

public class Cost {
    private Integer costId;

    private String name;

    private Integer baseDuration;

    private Integer baseCost;

    private Integer unitCost;

    private String status;

    private String descr;

    private String creatime;

    private String startime;

    private String costType;

    @Override
    public String toString() {
        return "Cost{" +
                "costId=" + costId +
                ", name='" + name + '\'' +
                ", baseDuration=" + baseDuration +
                ", baseCost=" + baseCost +
                ", unitCost=" + unitCost +
                ", status='" + status + '\'' +
                ", descr='" + descr + '\'' +
                ", creatime=" + creatime +
                ", startime=" + startime +
                ", costType='" + costType + '\'' +
                '}';
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(Integer baseDuration) {
        this.baseDuration = baseDuration;
    }

    public Integer getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Integer baseCost) {
        this.baseCost = baseCost;
    }

    public Integer getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Integer unitCost) {
        this.unitCost = unitCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Cost() {

    }

    public Cost(Integer costId, String name, Integer baseDuration, Integer baseCost, Integer unitCost, String status, String descr, String creatime, String startime, String costType) {

        this.costId = costId;
        this.name = name;
        this.baseDuration = baseDuration;
        this.baseCost = baseCost;
        this.unitCost = unitCost;
        this.status = status;
        this.descr = descr;
        this.creatime = creatime;
        this.startime = startime;
        this.costType = costType;
    }
}