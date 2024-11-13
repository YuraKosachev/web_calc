package tms.web_calcs.handlers;

import tms.web_calcs.enums.RequetMethod;

import java.util.Map;

/**
 * Author simonpirko
 * Created on 7.11.24
 */

// localhost:8080/greeting?name=Test&age=22 -> Hello Test

public class GreetingHandler extends BaseHandler {

    public GreetingHandler() {
        super(RequetMethod.GET);
    }

    @Override
    public String getResponse(Map<String, String> parameters) {
        String name = parameters.get("name");
        int age = Integer.parseInt(parameters.get("age"));

        return "Hello %s, %s!".formatted(name, age);
    }

}
