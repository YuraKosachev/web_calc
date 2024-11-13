package tms.web_calcs.enums;

public enum TemperatureUnitMeasure {
    CELSIUS('C'),
    FAHRENHEIT('F'),
    KELVIN('K');

    private final char unit;

    TemperatureUnitMeasure(char unit) {
        this.unit = unit;
    }

    public static TemperatureUnitMeasure getTemperatureUnitMeasure(char unit) {
        for (TemperatureUnitMeasure temp : TemperatureUnitMeasure.values()) {
            if (temp.unit == Character.toUpperCase(unit)) {
                return temp;
            }
        }
        throw new EnumConstantNotPresentException(TemperatureUnitMeasure.class,"%c not found".formatted(unit));
    }
}
