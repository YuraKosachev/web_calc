package tms.web_calcs;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Map;

public class CalculatingHandler extends BaseHandler {

    @Override
    public String getResponse(Map<String, String> parameters) {
        License.iConfirmNonCommercialUse("yuko_by");
        String queryExpression = parameters.get("expression");
        Expression expression = new Expression(queryExpression);

        return "%s = %.2f".formatted(expression.getExpressionString(), expression.calculate());
    }
}
