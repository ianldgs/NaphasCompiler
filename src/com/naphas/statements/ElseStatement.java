package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.interfaces.Statement;

public class ElseStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = new Parser();

        parser.match(Type.ELSE);

        if(parser.isEqualReceivedToken(Type.IF)) {
            IfStatement ifStatement = new IfStatement();
            ifStatement.execute();
        }

        BlockStatement blockStatement = new BlockStatement();
        blockStatement.execute();
    }
}
