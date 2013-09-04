package com.mileminder.domain;

import java.text.DecimalFormat;

public class Distance {
    private double value;
    private String units;

    public Distance() {
    }

    public Distance(float value, String units) {
        this.value = value;
        this.units = units;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return new DecimalFormat("#.##").format(value) + " " + units;
    }
}
