package tms.web_calcs.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import tms.web_calcs.enums.RequetMethod;
import tms.web_calcs.models.CalculationResponse;

import java.util.Map;

public class CalculatingHandler extends BaseHandler {

    public CalculatingHandler() {
        super(RequetMethod.GET);
    }

    @Override
    public String getResponse(Map<String, String> parameters) {
        License.iConfirmNonCommercialUse("yuko_by");
        String queryExpression = parameters.get("expression");
        Expression expression = new Expression(queryExpression);

        CalculationResponse calculationResponse = new CalculationResponse(expression.calculate());

        return new Gson().toJson(calculationResponse);
    }

    @Override
    protected void setAdditionalParameters(HttpExchange exchange) {
        super.setAdditionalParameters(exchange);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
    }
}
