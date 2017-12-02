package com.naphas.statements;

import com.naphas.exceptions.SyntaxException;

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
