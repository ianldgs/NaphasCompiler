package com.naphas.statements.operators;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.interfaces.ComparisionStatement;

public class RelationalStatement implements ComparisionStatement {

    @Override
    public void value() throws SyntaxException {
        Parser parser = Parser.getInstance();

        ValueStatement valueStatement = new ValueStatementBuilder()
                .allowFloat()
                .allowInt()
                .build();

        valueStatement.execute();
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
