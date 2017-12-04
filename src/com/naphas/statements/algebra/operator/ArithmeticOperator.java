package com.naphas.statements.algebra.operator;

import com.naphas.statements.algebra.equation.AlgebraEquation;
import com.naphas.statements.algebra.equation.ArithmeticEquation;
import com.naphas.statements.algebra.expression.AlgebraExpression;
import com.naphas.statements.algebra.expression.ArithmeticExpression;

public class ArithmeticOperator implements AlgebraOperator {
    @Override
    public AlgebraEquation equation() {
        return new ArithmeticEquation();
    }

    @Override
    public AlgebraExpression expression() {
        return new ArithmeticExpression();
    }
}
