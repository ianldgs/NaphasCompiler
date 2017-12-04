package com.naphas.statements.algebra.symbol;

import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.algebra.operator.AlgebraOperator;
import com.naphas.statements.interfaces.Statement;

import java.util.List;
import java.util.ListIterator;

public interface AlgebraSymbol extends Statement {
    @Override
    default void execute() throws SyntaxException {
        ListIterator<Type> listIteratorAcceptedSymbols = this.acceptedSymbols().listIterator();
        boolean isAnySymbolAllowed = Utils.validateSymbolsAllowed(listIteratorAcceptedSymbols);

        if (isAnySymbolAllowed) {
            this.operator().execute();
        }
    }

    List<Type> acceptedSymbols();

    ValueStatement acceptedValues();

    AlgebraOperator operator();
}
