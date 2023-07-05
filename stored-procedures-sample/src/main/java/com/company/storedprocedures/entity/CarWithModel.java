package com.company.storedprocedures.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class CarWithModel {
    private String vin;

    private Integer year;

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}