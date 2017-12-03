package com.naphas.statements.conditional;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;
import com.naphas.statements.expression.BooleanExpression;
import com.naphas.statements.interfaces.Statement;

public class IfStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.IF);
        parser.match(Type.START_EXP);

        BooleanExpression booleanExpression = new BooleanExpression();
        booleanExpression.execute();

        parser.match(Type.END_EXP);

        BlockStatement blockStatement = new BlockStatement();
        blockStatement.execute();
    }

    public static boolean isEqualStartStatement() {
        Parser parser = Parser.getInstance();
        return parser.isEqualReceivedToken(Type.IF);
    }
}
