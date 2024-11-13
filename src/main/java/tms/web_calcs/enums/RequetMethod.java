package tms.web_calcs.enums;

public enum RequetMethod {
    POST("POST"),
    PUT("PUT"),
    GET("GET"),
    DELETE("DELETE");

    private final String name;

    RequetMethod(String method) {
        this.name = method;
    }

    public boolean compareMethod(String method) {
        return this.name.equalsIgnoreCase(method);
    }
}
