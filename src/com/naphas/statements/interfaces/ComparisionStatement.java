package com.naphas.statements.interfaces;

import com.naphas.exceptions.SyntaxException;

public interface ComparisionStatement extends OperationStatement {
    void value() throws SyntaxException;

    @Override
    default void leftValue() throws SyntaxException {
        this.value();
    }

    @Override
    default void rightValue() throws SyntaxException {
        this.value();
    }
}
