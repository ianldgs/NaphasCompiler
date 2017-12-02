package com.naphas.statements;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.interfaces.Statement;

public class CaseStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.CASE);

        if(parser.isEqualReceivedToken(Type.LIT_STRING)) {
            parser.match(Type.LIT_STRING);
        }
        else if(parser.isEqualReceivedToken(Type.LIT_CHAR)) {
            parser.match(Type.LIT_CHAR);
        }
        else {
            parser.match(Type.LIT_INT);
        }

        BlockStatement blockStatement = new BlockStatement();
        blockStatement.execute();
    }

    public static boolean isEqualStartStatement() {
        Parser parser = Parser.getInstance();
        return parser.isEqualReceivedToken(Type.CASE);
    }
}
