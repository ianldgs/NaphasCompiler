package com.naphas.statements.expression;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.conditional.IfStatement;
import com.naphas.statements.conditional.SwitchStatement;
import com.naphas.statements.interfaces.Statement;

public class ConditionalExpression implements Statement {

    @Override
    public void execute() throws SyntaxException {
        Statement statement = null;

        if(SwitchStatement.isEqualStartStatement()) {
            statement = new SwitchStatement();
        }
        else if(IfStatement.isEqualStartStatement()) {
            statement = new IfStatement();
        }

        if(statement != null) {
            statement.execute();
        }
    }
}
