package com.naphas.statements.algebra.equation;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.expression.ExpressionStatement;
import com.naphas.statements.interfaces.Statement;
import com.naphas.statements.algebra.expression.LogicalExpression;
import com.naphas.statements.algebra.expression.RelationalExpression;

public class BooleanEquation extends ExpressionStatement {
    @Override
    public void execute() throws SyntaxException {
        Statement statement;

        if(LogicalExpression.isEqualStartToken()) {
            statement = new LogicalExpression();
        }
        else {
            statement = new RelationalExpression();
        }

        statement.execute();
    }

    public static boolean isEqualStartToken() {
        return  LogicalExpression.isEqualStartToken() &&
                RelationalExpression.isEqualStartToken();
    }
}
