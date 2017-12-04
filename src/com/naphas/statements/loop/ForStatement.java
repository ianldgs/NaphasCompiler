package com.naphas.statements.loop;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;
import com.naphas.statements.algebra.equation.ArithmeticEquation;
import com.naphas.statements.algebra.expression.ArithmeticExpression;

public class ForStatement extends LoopStatement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();
        parser.match(Type.FOR);

        super.execute();

        BlockStatement blockStatement = new BlockStatement();
        blockStatement.execute();
    }

    @Override
    protected void expression() throws SyntaxException {
        this.initialization();
        super.expression();
        this.increment();
    }

    private void initialization() throws SyntaxException {
        Parser parser = Parser.getInstance();
        parser.match(Type.TERMINATOR);
    }

    private void increment() throws SyntaxException {
        ArithmeticEquation arithmeticEquation = new ArithmeticEquation();
        arithmeticEquation.execute();
    }
}
