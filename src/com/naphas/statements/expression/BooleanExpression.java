package com.naphas.statements.expression;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.Statement;
import com.naphas.statements.operators.LogicalStatement;
import com.naphas.statements.operators.RelationalStatement;

public class BooleanExpression extends ExpressionStatement {
    @Override
    public void execute() throws SyntaxException {
        Statement statement;

        if(LogicalStatement.isEqualStartToken()) {
            statement = new LogicalStatement();
        }
        else {
            statement = new RelationalStatement();
        }

        statement.execute();
    }

    public static boolean isEqualStartToken() {
        return  LogicalStatement.isEqualStartToken() &&
                RelationalStatement.isEqualStartToken();
    }
}
