package tms.web_calcs.converters;

public final class TemperatureConverter {
    // Method to convert Celcius to Fahrenheit
    public static double cToF(double c){
        return (c * 9/5) + 32;
    }

    // Method to convert Celcius to Kelvin
    public static double cToK(double c){
        return c + 273.15;
    }

    // Method to convert Fahrenheit to Celcius
    public static double fToC(double f){
        return (f - 32) * 5/9;
    }

    // Method to convert Fahrenheit to Kelvin
    public static double fToK(double f){
        return (f - 32) * 5/9 + 273.15;
    }

    // Method to convert Kelvin to Celcius
    public static double kToC(double k){
        return k - 273.15;
    }

    // Method to convert Kelvin to Fahrenheit
    public static double kToF(double k){
        return (k - 273.15) * 9/5 + 32;
    }
}
