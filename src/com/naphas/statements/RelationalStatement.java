package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.ComparisionStatement;

public class RelationalStatement implements ComparisionStatement {

    @Override
    public void value() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if(parser.isEqualReceivedToken(Type.IDENTIFIER)) {
            parser.match(Type.IDENTIFIER);
        }
        else if(parser.isEqualReceivedToken(Type.LIT_FLOAT)) {
            parser.match(Type.LIT_FLOAT);
        }
        else {
            parser.match(Type.LIT_INT);
        }
    }

    @Override
    public void symbols() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if(parser.isEqualReceivedToken(Type.OP_GT)) {
            parser.match(Type.OP_GT);
        }
        else if(parser.isEqualReceivedToken(Type.OP_GTE)) {
            parser.match(Type.OP_GTE);
        }
        else if(parser.isEqualReceivedToken(Type.OP_LT)) {
            parser.match(Type.OP_LT);
        }
        else {
            parser.match(Type.OP_LTE);
        }
    }
}
