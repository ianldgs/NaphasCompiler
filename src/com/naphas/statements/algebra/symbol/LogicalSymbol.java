package com.naphas.statements.algebra.symbol;

import com.naphas.Type;
import com.naphas.Utils;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.algebra.operator.AlgebraOperator;
import com.naphas.statements.algebra.operator.BooleanOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogicalSymbol implements AlgebraSymbol {
    private static List<Type> list = Collections.unmodifiableList(
            new ArrayList<>() {{
                add(Type.OP_EQUAL);
                add(Type.OP_DIFF);
                add(Type.OP_AND);
                add(Type.OP_OR);
            }}
    );

    @Override
    public List<Type> acceptedSymbols() {
        return list;
    }

    @Override
    public ValueStatement acceptedValues() {
        return new ValueStatementBuilder()
                .allowNotIdentifier()
                .allowAllTypes()
                .build();
    }

    @Override
    public AlgebraOperator operator() {
        return new BooleanOperator();
    }

    public static boolean isEqualStartToken() {
        Type[] tokens = (Type[]) list.toArray();
        return Utils.isEqualStartToken(tokens);
    }
}
