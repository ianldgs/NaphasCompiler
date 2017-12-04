package com.naphas.statements.algebra.expression;

import com.naphas.statements.algebra.symbol.AlgebraSymbol;
import com.naphas.statements.algebra.symbol.LogicalSymbol;
import com.naphas.statements.algebra.symbol.RelationalSymbol;

public class BooleanExpression implements AlgebraExpression {

    @Override
    public AlgebraSymbol symbol() {
        AlgebraSymbol symbol;

        if (LogicalSymbol.isEqualStartToken()) {
            symbol = new LogicalSymbol();
        } else {
            symbol = new RelationalSymbol();
        }

        return symbol;
    }
}
