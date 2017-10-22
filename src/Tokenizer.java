public class Tokenizer {
    private enum State {
        INITIAL,
        IDENTIFIER,
    }

    private String code;

    private State state = State.INITIAL;

    private int position = 0;

    private String lexeme;

    public Tokenizer(String code) {
        this.code = code;
    }

    private char getNextCharacter() {
        return code.charAt(position++);
    }

    public Token getNextToken() throws Exception {
        while (position < code.length()) {
            char c = getNextCharacter();

            switch (state) {
                case INITIAL:
                    if (c == '=') {
                        return Token.OP_ATTRIB;
                    }
            }

            throw new Exception("Invalid identifier: " + c);
        }

        return null;
    }
}
