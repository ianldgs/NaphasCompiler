package com.naphas;

import com.naphas.exceptions.SyntaxException;

import java.util.ListIterator;

public class Utils {

    public static void validateTypesAllowed(ListIterator<Type> allowedTypes) throws SyntaxException {
        Parser parser = Parser.getInstance();

        while(allowedTypes.hasNext()) {
            Type type = allowedTypes.next();

            if(parser.isEqualReceivedToken(Type.OP_NOT)) {
                parser.match(Type.OP_NOT);
            }
        }
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
