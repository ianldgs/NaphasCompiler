package com.naphas.statements.interfaces;

import com.naphas.exceptions.SyntaxException;

public interface OperationStatement extends Statement {
    @Override
    default void execute() throws SyntaxException {
        this.leftValue();

        this.symbols();

        this.rightValue();
    }

    void leftValue() throws SyntaxException;
    void symbols() throws SyntaxException;
    void rightValue() throws SyntaxException;
}
