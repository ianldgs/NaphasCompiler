package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.Statement;

import java.util.ListIterator;

public class ValueStatement implements Statement {
    private ListIterator<Type> allowedTypes;
    private boolean allowedNotIdentifier = false;

    public ValueStatement(ListIterator<Type> allowedTypes, boolean allowedNotIdentifier) {
        this.allowedTypes = allowedTypes;
        this.allowedNotIdentifier = allowedNotIdentifier;
    }

    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        while(this.allowedTypes.hasNext()) {
            Type type = this.allowedTypes.next();

            if(parser.isEqualReceivedToken(Type.OP_NOT)) {
                parser.match(Type.OP_NOT);
            }
        }

        if(this.allowedNotIdentifier && parser.isEqualReceivedToken(Type.OP_NOT)) {
            parser.match(Type.OP_NOT);
        }

        parser.match(Type.IDENTIFIER);
    }
}
