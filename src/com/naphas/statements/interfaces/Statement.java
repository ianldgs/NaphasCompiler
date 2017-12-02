package com.naphas.statements.interfaces;

import com.naphas.exceptions.SyntaxException;

public interface Statement {
    void execute() throws SyntaxException;
}
