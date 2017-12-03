package com.naphas;

public class Utils {

    public static boolean isEqualStartToken(Type[] tokens) {
        Parser parser = Parser.getInstance();

        boolean isEqual = false;

        for (int i = 0; i < tokens.length; i++) {
            isEqual = parser.isEqualReceivedToken(tokens[i]) || isEqual;
        }

        return isEqual;
    }
}
