package tms.web_calcs.models;

import tms.web_calcs.enums.TemperatureUnitMeasure;

public class ConvertResponse {
    private final TemperatureUnitMeasure unitMeasure;
    private final double value;

    public ConvertResponse(TemperatureUnitMeasure unitMeasure, double value) {
        this.unitMeasure = unitMeasure;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public TemperatureUnitMeasure getUnitMeasure() {
        return unitMeasure;
    }
}
