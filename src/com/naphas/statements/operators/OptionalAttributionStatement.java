package com.naphas.statements.operators;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;

public class OptionalAttributionStatement extends AttributionStatement {
    @Override
    public void execute() throws SyntaxException {
        super.leftValue();

        Parser parser = Parser.getInstance();

        if (parser.isEqualReceivedToken(Type.OP_ATTRIB)) {
            super.symbols();
            super.rightValue();
        }
    }
}
