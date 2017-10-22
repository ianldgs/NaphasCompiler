public class Tokenizer {
    private enum State {
        INITIAL
    }

    private String code;

    private State state = State.INITIAL;

    private int position = 0;

    public Tokenizer(String code) {
        this.code = code;
    }

    private char getNextCharacter() {
        return code.charAt(position++);
    }

    public Token getNextToken() {
        switch (state) {
            case INITIAL:
                return Token.IDENTIFIER;
        }

        return Token.IDENTIFIER;
    }
}
