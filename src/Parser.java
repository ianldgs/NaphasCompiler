import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getNextToken() {
        int nextIndex = position + 1;
        return this.tokens.get(nextIndex);
    }

    private void consume() {
        this.position++;
    }

    public boolean match(Token expected) throws SyntaxException {
        Token received = this.getNextToken();

        boolean isEqualReceivedToken = received.equals(expected);
        this.consume();

        if (isEqualReceivedToken) {
            return isEqualReceivedToken;
        }
        else {
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