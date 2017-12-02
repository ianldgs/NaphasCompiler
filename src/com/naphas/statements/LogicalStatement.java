package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.interfaces.Statement;

public class LogicalStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if(parser.isEqualReceivedToken(Type.OP_NOT)) {
            parser.match(Type.OP_NOT);
        }

        this.value();

        if(parser.isEqualReceivedToken(Type.OP_EQUAL)) {
            parser.match(Type.OP_EQUAL);
        }
        else if(parser.isEqualReceivedToken(Type.OP_DIFF)) {
            parser.match(Type.OP_DIFF);
        }
        else if(parser.isEqualReceivedToken(Type.OP_AND)) {
            parser.match(Type.OP_AND);
        }
        else {
            parser.match(Type.OP_OR);
        }

        this.value();
    }

    private void value() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if(parser.isEqualReceivedToken(Type.IDENTIFIER)) {
            parser.match(Type.IDENTIFIER);
        }
        else {
            parser.match(Type.TYPE_BOOLEAN);
        }
    }
}
