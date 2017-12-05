package com.naphas.statements.expression;

import com.naphas.Parser;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.BlockStatement;
import com.naphas.statements.interfaces.Statement;

public class ExpressionStatement implements Statement {
    @Override
    public void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        while (!parser.isEndOfFile() || !BlockStatement.isEqualEndStatement()) {
            Statement statement = null;

            if(LoopExpression.isEqualStartStatement()) {
                statement = new LoopExpression();
            }
            else if(ConditionalExpression.isEqualStartStatement()) {
                statement = new ConditionalExpression();
            }

            statement.execute();
        }
    }
}
