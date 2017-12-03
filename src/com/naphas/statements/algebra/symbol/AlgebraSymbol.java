package com.naphas.statements.algebra.symbol;

import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.algebra.operator.AlgebraOperator;
import com.naphas.statements.interfaces.Statement;

import java.util.ListIterator;

public interface AlgebraSymbol extends Statement {
    @Override
    default void execute() throws SyntaxException {
        Utils.validateTypesAllowed(this.acceptedSymbols());

        AlgebraOperator algebraOperator = this.operator();
    }

    ListIterator<Type> acceptedSymbols();

    AlgebraOperator operator();
}
