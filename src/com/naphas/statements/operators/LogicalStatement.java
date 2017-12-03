package com.naphas.statements.operators;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
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

        ValueStatement valueStatement = new ValueStatementBuilder()
                .allowNotIdentifier()
                .allowAllTypes()
                .build();

        valueStatement.execute();
    }

    public static boolean isEqualStartToken() {
        Parser parser = Parser.getInstance();
        Type[] tokens = { Type.OP_EQUAL, Type.OP_DIFF, Type.OP_DIFF, Type.OP_AND, Type.OP_OR };

        boolean isEqual = false;

        for (int i = 0; i < tokens.length; i++) {
            isEqual = parser.isEqualReceivedToken(tokens[i]) || isEqual;
        }

        return isEqual;
    }
}
