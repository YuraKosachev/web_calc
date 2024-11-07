package tms.web_calcs;

//import javax.script.ScriptException;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Map;

public class CalculatingHandler extends BaseHandler {

    @Override
    public String getResponse(Map<String, String> parameters) {

        String queryExpression = parameters.get("expression");
        Expression expression = new Expression(queryExpression);

        return "%s = %.2f".formatted(expression.getExpressionString(), expression.calculate());
    }
}
