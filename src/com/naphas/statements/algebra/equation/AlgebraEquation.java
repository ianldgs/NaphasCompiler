package com.naphas.statements.algebra.equation;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.algebra.expression.AlgebraExpression;
import com.naphas.statements.interfaces.Statement;

public interface AlgebraEquation extends Statement {
    @Override
    default void execute() throws SyntaxException {
        AlgebraExpression algebraExpression = this.expression();
        algebraExpression.execute();

        algebraExpression.symbol().execute();
    }

    AlgebraExpression expression();
}
