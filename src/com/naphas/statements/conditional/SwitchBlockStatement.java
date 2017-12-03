package com.naphas.statements.conditional;

import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;
import com.naphas.statements.conditional.CaseStatement;

public class SwitchBlockStatement extends BlockStatement {
    @Override
    protected void executeBlockContent() throws SyntaxException {
        do {
            CaseStatement caseStatement = new CaseStatement();
            caseStatement.execute();
        }
        while(CaseStatement.isEqualStartStatement());
    }
}
