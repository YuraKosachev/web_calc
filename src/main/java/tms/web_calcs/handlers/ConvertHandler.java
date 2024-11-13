package tms.web_calcs.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import tms.web_calcs.converters.TemperatureConverter;
import tms.web_calcs.enums.TemperatureUnitMeasure;
import tms.web_calcs.models.ConvertRequest;
import tms.web_calcs.models.ConvertResponse;
import tms.web_calcs.enums.RequetMethod;
import tms.web_calcs.exceptions.ArgumentException;

import java.util.Map;

public class ConvertHandler extends BaseHandler {

    public ConvertHandler() {
        super(RequetMethod.POST);
    }

    @Override
    public String getResponse(Map<String, String> parameters) throws ArgumentException {
        if(!parameters.containsKey("body")){
            throw new ArgumentException("body parameter is't found");
        }
        Gson gson = new Gson();
        String body = parameters.get("body");

        ConvertRequest req = gson.fromJson(body, ConvertRequest.class);
        ConvertResponse response = new ConvertResponse(req.getTo(), getTemperature(req));

        return gson.toJson(response);
    }

    private double getTemperature(ConvertRequest request) throws ArgumentException {
        if(request.getFrom() == request.getTo())
        {
            return request.getValue();
        }

        if(request.getFrom() == TemperatureUnitMeasure.CELSIUS)
        {
          return switch (request.getTo()){
              case KELVIN -> TemperatureConverter.cToK(request.getValue());
              case FAHRENHEIT -> TemperatureConverter.cToF(request.getValue());
              default -> throw new ArgumentException("Converter not found");
          };
        } else if (request.getFrom() == TemperatureUnitMeasure.KELVIN) {
            return switch (request.getTo()){
                case CELSIUS -> TemperatureConverter.kToC(request.getValue());
                case FAHRENHEIT -> TemperatureConverter.kToF(request.getValue());
                default -> throw new ArgumentException("Converter not found");
            };
        } else if (request.getFrom() == TemperatureUnitMeasure.FAHRENHEIT) {
            return switch (request.getTo()){
                case KELVIN -> TemperatureConverter.fToK(request.getValue());
                case CELSIUS -> TemperatureConverter.fToC(request.getValue());
                default -> throw new ArgumentException("Converter not found");
            };
        }

        throw new ArgumentException("Converter not found");
    }

    @Override
    protected void setAdditionalParameters(HttpExchange exchange) {
        super.setAdditionalParameters(exchange);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
    }
}
