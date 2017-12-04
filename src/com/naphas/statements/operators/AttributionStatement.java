package com.naphas.statements.operators;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.interfaces.OperationStatement;

public class AttributionStatement implements OperationStatement {
    @Override
    public void leftValue() throws SyntaxException {
        ValueStatement valueStatement = new ValueStatement();
        valueStatement.execute();
    }

    @Override
    public void symbols() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if (parser.isEqualReceivedToken(Type.OP_ADD_SET)) {
            parser.match(Type.OP_ADD_SET);
        } else if (parser.isEqualReceivedToken(Type.OP_SUB_SET)) {
            parser.match(Type.OP_SUB_SET);
        } else {
            parser.match(Type.OP_ATTRIB);
        }
    }

    @Override
    public void rightValue() throws SyntaxException {
        ValueStatement valueStatement = new ValueStatementBuilder()
                .allowNotIdentifier()
                .allowAllTypes()
                .build();

        valueStatement.execute();
    }
}
