package tms.web_calcs.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tms.web_calcs.enums.RequetMethod;
import tms.web_calcs.enums.StatusCode;
import tms.web_calcs.exceptions.ArgumentException;
import tms.web_calcs.exceptions.HandlerNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class BaseHandler implements HttpHandler {

    final RequetMethod handlerMethod;

    public BaseHandler(RequetMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    final Set<String> bodyMethods = new HashSet<>(List.of(RequetMethod.POST.name(), RequetMethod.PUT.name()));

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        String message = null;
        int statusCode = StatusCode.OK.getCode();

        try {
            if(!handlerMethod.compareMethod(method)){
                throw new HandlerNotFoundException("handler does not work with this request method");
            }

            String query = exchange.getRequestURI().getQuery();
            Map<String, String> parameters = Optional.ofNullable(getParameters(query)).orElse(new HashMap<>());

            if (bodyMethods.contains(method)) {
                Optional<String> body = Optional.ofNullable(getBody(exchange));
                body.ifPresent(s -> parameters.put("body", s));
            }

            message = getResponse(parameters);
            setAdditionalParameters(exchange);
        }
        catch (HandlerNotFoundException ex) {
            statusCode = StatusCode.NOTFOUND.getCode();
            message = "error : %s".formatted(ex.getMessage());
        }
        catch (ArgumentException ex) {
            statusCode = StatusCode.BAD.getCode();
            message = "error : %s".formatted(ex.getMessage());
        } catch (Exception ex) {
            statusCode = StatusCode.SERVERERROR.getCode();
            message = "error : %s".formatted(ex.getMessage());
        } finally {
            exchange.sendResponseHeaders(statusCode, message.length());
            exchange.getResponseBody().write(message.getBytes());
            exchange.getResponseBody().close();
        }
    }

    private String getBody(HttpExchange exchange) throws IOException {
        try (InputStream stream = exchange.getRequestBody()) {
            byte[] bytes = stream.readAllBytes();
            return new String(bytes);
        }
    }

    protected Map<String, String> getParameters(String query) {
        Map<String, String> map = new HashMap<>();
        if(query == null)
            return null;
        String[] split = query.split("&");
        for (String s : split) {
            String[] split1 = s.split("=");
            map.put(split1[0], split1[1]);
        }
        return map;
    }

    protected void setAdditionalParameters(HttpExchange exchange) {
    }

    public abstract String getResponse(Map<String, String> parameters) throws ArgumentException;
}
