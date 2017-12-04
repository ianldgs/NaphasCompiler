package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.Statement;

import java.util.Collections;
import java.util.ListIterator;

public class ValueStatement implements Statement {
    private ListIterator<Type> allowedTypes = Collections.emptyListIterator();
    private boolean allowedNotIdentifier = false;

    public ValueStatement() {
    }

    public ValueStatement(ListIterator<Type> allowedTypes, boolean allowedNotIdentifier) {
        this.allowedTypes = allowedTypes;
        this.allowedNotIdentifier = allowedNotIdentifier;
    }

    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        Utils.validateTypesAllowed(this.allowedTypes);

        if (this.allowedNotIdentifier && parser.isEqualReceivedToken(Type.OP_NOT)) {
            parser.match(Type.OP_NOT);
        }

        parser.match(Type.IDENTIFIER);
    }
}
