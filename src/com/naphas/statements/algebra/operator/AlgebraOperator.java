package com.naphas.statements.algebra.operator;

import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.algebra.equation.AlgebraEquation;
import com.naphas.statements.algebra.expression.AlgebraExpression;
import com.naphas.statements.interfaces.Statement;

public interface AlgebraOperator extends Statement {
    @Override
    default void execute() throws SyntaxException {
        AlgebraEquation equation = this.equation();
        equation.execute();

        AlgebraExpression expression = this.expression();
        expression.execute();
    }

    AlgebraEquation equation();

    AlgebraExpression expression();

}
