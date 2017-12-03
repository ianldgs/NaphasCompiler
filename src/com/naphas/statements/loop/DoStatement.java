package com.naphas.statements.loop;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;

public class DoStatement extends BlockStatement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();
        parser.match(Type.DO);

        super.execute();
    }
}
