package com.naphas.statements.algebra.expression;

import com.naphas.statements.algebra.symbol.ArithmeticSymbol;

public class ArithmeticExpression implements AlgebraExpression {


    @Override
    public ArithmeticSymbol symbol() {
        return new ArithmeticSymbol();
    }
}
