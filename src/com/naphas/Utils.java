package com.naphas;

import com.naphas.exceptions.SyntaxException;

import java.util.ListIterator;

public class Utils {

    public static boolean validateSymbolsAllowed(ListIterator<Type> allowedSymbols) throws SyntaxException {
        Parser parser = Parser.getInstance();

        boolean isAnySymbolAllowed = false;

        while (allowedSymbols.hasNext()) {
            Type type = allowedSymbols.next();

            if (parser.isEqualReceivedToken(type)) {
                isAnySymbolAllowed = true;
                parser.match(type);
            }
        }

        return isAnySymbolAllowed;
    }

    public static boolean isEqualStartToken(Type[] tokens) {
        Parser parser = Parser.getInstance();

        boolean isEqual = false;

        for (int i = 0; i < tokens.length; i++) {
            isEqual = parser.isEqualReceivedToken(tokens[i]) || isEqual;
        }

        return isEqual;
    }

    public static boolean isEqualStartStatement(Type type) {
        Parser parser = Parser.getInstance();
        return parser.isEqualReceivedToken(type);
    }
}
