package com.naphas.statements.loop;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.expression.BooleanExpression;
import com.naphas.statements.interfaces.Statement;

public class LoopStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.START_EXP);
        this.expression();
        parser.match(Type.END_EXP);
    }

    private void termination() throws SyntaxException {
        Parser parser = Parser.getInstance();

        BooleanExpression booleanExpression = new BooleanExpression();
        booleanExpression.execute();

        parser.match(Type.TERMINATOR);
    }

    protected void expression() throws SyntaxException {
        this.termination();
    }
}