package tms.web_calcs.models;

public class CalculationResponse {
    private final double result;
    public CalculationResponse(double result) {
        this.result = result;
    }
    public double getResult() {
        return result;
    }
}
