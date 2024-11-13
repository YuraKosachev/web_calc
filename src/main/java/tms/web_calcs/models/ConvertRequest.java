package tms.web_calcs.models;

import tms.web_calcs.enums.TemperatureUnitMeasure;

public class ConvertRequest {
    private final double value;
    private final TemperatureUnitMeasure from;
    private final TemperatureUnitMeasure to;

    public ConvertRequest(double value, TemperatureUnitMeasure from, TemperatureUnitMeasure temperatureUnitMeasure) {
        this.value = value;
        this.from = from;
        to = temperatureUnitMeasure;
    }

    public double getValue() {
        return value;
    }

    public TemperatureUnitMeasure getFrom() {
        return from;
    }

    public TemperatureUnitMeasure getTo() {
        return to;
    }
}
