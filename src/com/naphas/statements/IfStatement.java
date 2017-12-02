package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.interfaces.Statement;

public class IfStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.IF);
        parser.match(Type.START_EXP);

        LogicalStatement logicalStatement = new LogicalStatement();
        logicalStatement.execute();

        parser.match(Type.END_EXP);

        BlockStatement blockStatement = new BlockStatement();
        blockStatement.execute();
    }
}
