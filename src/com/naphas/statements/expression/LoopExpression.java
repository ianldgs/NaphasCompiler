package com.naphas.statements.expression;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.Statement;
import com.naphas.statements.loop.ForStatement;
import com.naphas.statements.loop.WhileStatement;

public class LoopExpression implements Statement {

    @Override
    public void execute() throws SyntaxException {
        Statement statement = null;

        if(ForStatement.isEqualStartStatement()) {
            statement = new ForStatement();
        }
        else if (WhileStatement.isEqualStartStatement()) {
            statement = new WhileStatement();
        }

        if(isEqualStartStatement()) {
            statement.execute();
        }
    }

    public static boolean isEqualStartStatement() {
        return (
                ForStatement.isEqualStartStatement() ||
                WhileStatement.isEqualStartStatement()
        );
    }
}
