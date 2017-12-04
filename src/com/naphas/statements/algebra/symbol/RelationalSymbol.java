package com.naphas.statements.algebra.symbol;

import com.naphas.Type;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.algebra.operator.AlgebraOperator;
import com.naphas.statements.algebra.operator.BooleanOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelationalSymbol implements AlgebraSymbol {
    private static List<Type> list = Collections.unmodifiableList(
            new ArrayList<>() {{
                add(Type.OP_GT);
                add(Type.OP_GTE);
                add(Type.OP_LT);
                add(Type.OP_LT);
            }}
    );

    @Override
    public List<Type> acceptedSymbols() {
        return list;
    }

    @Override
    public ValueStatement acceptedValues() {
        return new ValueStatementBuilder()
                .allowFloat()
                .allowInt()
                .build();
    }

    @Override
    public AlgebraOperator operator() {
        return new BooleanOperator();
    }
}
