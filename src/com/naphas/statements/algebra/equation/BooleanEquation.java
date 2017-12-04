package com.naphas.statements.algebra.equation;

import com.naphas.statements.algebra.expression.AlgebraExpression;
import com.naphas.statements.algebra.expression.BooleanExpression;

public class BooleanEquation implements AlgebraEquation {

    @Override
    public AlgebraExpression expression() {
        return new BooleanExpression();
    }
}
