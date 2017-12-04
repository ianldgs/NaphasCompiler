package com.naphas.statements;

import com.naphas.Type;

import java.util.ArrayList;
import java.util.List;

public class ValueStatementBuilder {
    private List<Type> allowedTypes = new ArrayList<Type>();
    private boolean allowNotIdentifier = false;

    public ValueStatementBuilder allowAllTypes() {
        this.allowBoolean()
                .allowChar()
                .allowString()
                .allowFloat()
                .allowInt();

        return this;
    }

    public ValueStatementBuilder allowNotIdentifier() {
        this.allowNotIdentifier = true;
        return this;
    }

    public ValueStatementBuilder allowString() {
        this.allowedTypes.add(Type.LIT_STRING);
        return this;
    }

    public ValueStatementBuilder allowInt() {
        this.allowedTypes.add(Type.LIT_INT);
        return this;
    }

    public ValueStatementBuilder allowFloat() {
        this.allowedTypes.add(Type.LIT_FLOAT);
        return this;
    }

    public ValueStatementBuilder allowNumber() {
        this.allowInt()
                .allowFloat();

        return this;
    }

    public ValueStatementBuilder allowChar() {
        this.allowedTypes.add(Type.LIT_CHAR);
        return this;
    }

    public ValueStatementBuilder allowBoolean() {
        this.allowedTypes.add(Type.LIT_BOOLEAN_FALSE);
        this.allowedTypes.add(Type.LIT_BOOLEAN_TRUE);
        return this;
    }

    public ValueStatement build() {
        return new ValueStatement(allowedTypes.listIterator(), this.allowNotIdentifier);
    }
}
