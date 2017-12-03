package com.naphas.statements.algebra.equation;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.expression.ExpressionStatement;
import com.naphas.statements.algebra.expression.ArithmeticExpression;

public class ArithmeticEquation extends ExpressionStatement {
    @Override
    public void execute() throws SyntaxException {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        arithmeticExpression.execute();
    }
}
