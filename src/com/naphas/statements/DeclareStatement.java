package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.Statement;
import com.naphas.statements.operators.AttributionStatement;
import com.naphas.statements.operators.OptionalAttributionStatement;

public class DeclareStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        if(parser.isEqualReceivedToken(Type.CREATE_VAR)) {
            parser.match(Type.CREATE_VAR);
        }
        else {
            parser.match(Type.CREATE_CONST);
        }

        AttributionStatement attributionStatement = new OptionalAttributionStatement();
        attributionStatement.execute();

        while(!parser.isEqualReceivedToken(Type.TERMINATOR)) {
            parser.match(Type.SEPARATOR);
            attributionStatement.execute();
        }

        parser.match(Type.TERMINATOR);
    }
}
