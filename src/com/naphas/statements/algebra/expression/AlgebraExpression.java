package com.naphas.statements.algebra.expression;

import com.naphas.Parser;
import com.naphas.Type;
import com.naphas.exceptions.SyntaxException;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.algebra.symbol.AlgebraSymbol;
import com.naphas.statements.interfaces.MirroredOperationStatement;
import com.naphas.statements.interfaces.Statement;

public interface AlgebraExpression extends Statement {

    @Override
    default void execute() throws SyntaxException {
        Parser parser = Parser.getInstance();

        boolean isExpressionWrapped = parser.isEqualReceivedToken(Type.START_EXP);

        if(isExpressionWrapped) {
            parser.match(Type.START_EXP);
        }

        this.acceptedValues();

        AlgebraSymbol algebraSymbol = this.symbol();
        algebraSymbol.execute();

        if(isExpressionWrapped) {
            parser.match(Type.END_EXP);
        }
    }

    AlgebraSymbol symbol();

    ValueStatement acceptedValues();
}
