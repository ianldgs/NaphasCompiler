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
}