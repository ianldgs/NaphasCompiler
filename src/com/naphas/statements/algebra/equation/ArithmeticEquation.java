package com.naphas.statements.algebra.equation;

import com.naphas.statements.algebra.expression.AlgebraExpression;
import com.naphas.statements.algebra.expression.ArithmeticExpression;

public class ArithmeticEquation implements AlgebraEquation {

    @Override
    public AlgebraExpression expression() {
        return new ArithmeticExpression();
    }
}
