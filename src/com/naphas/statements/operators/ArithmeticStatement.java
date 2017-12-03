package com.naphas.statements.operators;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.interfaces.ComparisionStatement;

public class ArithmeticStatement implements ComparisionStatement {

    @Override
    public void value() throws SyntaxException {
        ValueStatement valueStatement = new ValueStatementBuilder()
                .allowNumber()
                .build();

        valueStatement.execute();
    }

    @Override
    public void symbols() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if(parser.isEqualReceivedToken(Type.OP_ADD)) {
            parser.match(Type.OP_ADD);
        }
        else if(parser.isEqualReceivedToken(Type.OP_SUB)) {
            parser.match(Type.OP_SUB);
        }
        else if(parser.isEqualReceivedToken(Type.OP_MULT)) {
            parser.match(Type.OP_MULT);
        }
        else if(parser.isEqualReceivedToken(Type.OP_DIV)) {
            parser.match(Type.OP_DIV);
        }
        else if(parser.isEqualReceivedToken(Type.OP_MOD)) {
            parser.match(Type.OP_MOD);
        }
        else {
            parser.match(Type.OP_EXP);
        }
    }
}
