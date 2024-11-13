package tms.web_calcs.enums;

public enum StatusCode {
    OK(200),
    BAD(400),
    NOTFOUND(404),
    SERVERERROR(500);

    private final int code;
    StatusCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
