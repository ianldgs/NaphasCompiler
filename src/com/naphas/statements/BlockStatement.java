package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.interfaces.Statement;

public class BlockStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.START_BLOCK);

        while(!isEqualEndStatement()) {
            ExpressionStatement expressionStatement = new ExpressionStatement();
            expressionStatement.execute();
        }

        parser.match(Type.END_BLOCK);
    }

    public static boolean isEqualEndStatement() {
        Parser parser = Parser.getInstance();
        return parser.isEqualReceivedToken(Type.END_BLOCK);
    }
}
