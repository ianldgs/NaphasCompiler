package com.naphas.statements.algebra.operator;

import com.naphas.statements.algebra.equation.AlgebraEquation;
import com.naphas.statements.algebra.equation.BooleanEquation;
import com.naphas.statements.algebra.expression.AlgebraExpression;
import com.naphas.statements.algebra.expression.BooleanExpression;

public class BooleanOperator implements AlgebraOperator {
    @Override
    public AlgebraEquation equation() {
        return new BooleanEquation();
    }

    @Override
    public AlgebraExpression expression() {
        return new BooleanExpression();
    }
}
