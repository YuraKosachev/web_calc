package tms.web_calcs;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author simonpirko
 * Created on 7.11.24
 */

// localhost:8080/greeting?name=Test&age=22 -> Hello Test

public class GreetingHandler extends BaseHandler {

    @Override
    public String getResponse(Map<String, String> parameters) {
        String name = parameters.get("name");
        int age = Integer.parseInt(parameters.get("age"));

        return "Hello %s, %s!".formatted(name, age);
    }

}
