package com.naphas.statements.loop;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;

public class WhileStatement extends LoopStatement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        boolean isDoWhile = parser.isEqualReceivedToken(Type.DO);

        if (isDoWhile) {
            DoStatement doStatement = new DoStatement();
            doStatement.execute();
        }

        parser.match(Type.WHILE);
        super.execute();

        if (!isDoWhile) {
            BlockStatement blockStatement = new BlockStatement();
            blockStatement.execute();
        }
    }

    public static boolean isEqualStartStatement() {
        return Utils.isEqualStartStatement(Type.DO) || Utils.isEqualStartStatement(Type.WHILE);
    }
}
