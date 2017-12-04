package com.naphas;

import com.naphas.exceptions.SyntaxException;

import java.util.List;

public final class Parser {
    private List<Token> tokens;
    private int position = 0;

    private static final Parser INSTANCE = new Parser();

    public static Parser getInstance() {
        return INSTANCE;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Type getNextTokenType() {
        int nextIndex = position + 1;

        return this.tokens.get(nextIndex).getType();
    }

    private void consume() {
        this.position++;
    }

    public boolean isEqualReceivedToken(Type expected) {
        Type received = this.getNextTokenType();

        return received.equals(expected);
    }

    public boolean match(Type expected) throws SyntaxException {
        Type received = this.getNextTokenType();

        boolean isEqualReceivedToken = this.isEqualReceivedToken(expected);
        this.consume();

        if (isEqualReceivedToken) {
            return isEqualReceivedToken;
        } else {
            String message = "Expected token %1, but got %2 instead.";

            message = String.format(
                    message,
                    expected,
                    received
            );

            throw new SyntaxException(message);
        }
    }
}