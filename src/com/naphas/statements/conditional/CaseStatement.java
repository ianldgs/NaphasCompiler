package com.naphas.statements.conditional;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.interfaces.Statement;

public class CaseStatement extends BlockStatement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        parser.match(Type.CASE);

        ValueStatement valueStatement = new ValueStatementBuilder()
                .allowChar()
                .allowInt()
                .allowString()
                .build();

        valueStatement.execute();

        super.execute();
    }

    public static boolean isEqualStartStatement() {
        return Utils.isEqualStartStatement(Type.CASE);
    }
}
