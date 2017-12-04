package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.expression.ExpressionStatement;
import com.naphas.statements.interfaces.Statement;

public class BlockStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.START_BLOCK);

        this.executeBlockContent();

        parser.match(Type.END_BLOCK);
    }

    protected void executeBlockContent() throws SyntaxException {
        while (!isEqualEndStatement()) {
            ExpressionStatement expressionStatement = new ExpressionStatement();
            expressionStatement.execute();
        }
    }

    public static boolean isEqualEndStatement() {
        Parser parser = Parser.getInstance();
        return parser.isEqualReceivedToken(Type.END_BLOCK);
    }
}
