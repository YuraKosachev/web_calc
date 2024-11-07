package tms.web_calcs;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseHandler implements HttpHandler {
    final int okStatusCode = 200;
    final int badStatusCode = 500;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> parameters = getParameters(query);
        String message = null;
        int statusCode = okStatusCode;
        try {
            message = getResponse(parameters);
        } catch (Exception ex) {
            statusCode = badStatusCode;
            message = "error : %s".formatted(ex.getMessage());
        } finally {
            exchange.sendResponseHeaders(statusCode, message.length());
            exchange.getResponseBody().write(message.getBytes());
            exchange.getResponseBody().close();
        }
    }

    private Map<String, String> getParameters(String query) {
        Map<String, String> greetings = new HashMap<>();
        String[] split = query.split("&");
        for (String s : split) {
            String[] split1 = s.split("=");
            greetings.put(split1[0], split1[1]);
        }
        return greetings;
    }

    public abstract String getResponse(Map<String, String> parameters);
}
