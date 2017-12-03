package com.naphas.statements.conditional;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.interfaces.Statement;

public class SwitchStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();
        parser.match(Type.SWITCH);

        parser.match(Type.START_EXP);
        parser.match(Type.IDENTIFIER);
        parser.match(Type.END_EXP);

        SwitchBlockStatement switchBlockStatement = new SwitchBlockStatement();
        switchBlockStatement.execute();
    }
}
