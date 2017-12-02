package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.ComparisionStatement;

public class LogicalStatement implements ComparisionStatement {
    @Override
    public void symbols() throws SyntaxException {
        Parser parser = Parser.getInstance();

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
    }

    @Override
    public void value() throws SyntaxException {
        Parser parser = Parser.getInstance();

        switch (parser.getNextTokenType()) {
            case IDENTIFIER:
                if(parser.isEqualReceivedToken(Type.OP_NOT)) {
                    parser.match(Type.OP_NOT);
                }

                parser.match(Type.IDENTIFIER);
                break;

            case LIT_STRING:
                parser.match(Type.LIT_STRING);
                break;

            case LIT_INT:
                parser.match(Type.LIT_INT);
                break;

            case LIT_FLOAT:
                parser.match(Type.LIT_FLOAT);
                break;

            case LIT_CHAR:
                parser.match(Type.LIT_CHAR);
                break;

            default:
                parser.match(Type.LIT_BOOLEAN_FALSE);
                break;
        }
    }
}
