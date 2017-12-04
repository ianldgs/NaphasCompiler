package com.naphas.statements.algebra.symbol;

import com.naphas.Type;
import com.naphas.statements.ValueStatement;
import com.naphas.statements.ValueStatementBuilder;
import com.naphas.statements.algebra.operator.AlgebraOperator;
import com.naphas.statements.algebra.operator.ArithmeticOperator;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticSymbol implements AlgebraSymbol {
    @Override
    public List<Type> acceptedSymbols() {
        List<Type> list = new ArrayList<Type>();

        list.add(Type.OP_ADD);
        list.add(Type.OP_SUB);
        list.add(Type.OP_MULT);
        list.add(Type.OP_DIV);
        list.add(Type.OP_MOD);
        list.add(Type.OP_EXP);

        return list;
    }

    @Override
    public ValueStatement acceptedValues() {
        return new ValueStatementBuilder()
                .allowNumber()
                .build();
    }

    @Override
    public AlgebraOperator operator() {
        return new ArithmeticOperator();
    }
}
