public class Tokenizer {
    private enum State {
        INITIAL,
        IDENTIFIER,
        C,
        CO,
        CON,
        CONS,
        CONST,
        POSSIBLE_LET,
        ATTRIB,
        SINGLE_QUOTE_START,
        CHAR,
        SINGLE_QUOTE_END,
        TERMINATOR,
    }

    private String code;

    private State state = State.INITIAL;

    private int position = 0;

    private String lexeme;

    public Tokenizer(String code) {
        this.code = code + "$";
    }

    private char getNextCharacter() {
        return code.charAt(position++);
    }

    private void back() {
        back(1);
    }

    private void back(int n) {
        position -= n;
    }

    public Token getNextToken() throws Exception {
        while (position < code.length()) {
            char c = getNextCharacter();

            System.out.println("---------");
            System.out.println(state);
            System.out.println(c);

            switch (state) {
                case INITIAL:
                    if (c == 'c') {
                        state = State.C;
                    } else if (c == 'l') {
                        state = State.POSSIBLE_LET;
                    } else if (c == '=') {
                        state = State.ATTRIB;
                    } else if (c == '\'') {
                        state = State.SINGLE_QUOTE_START;
                    } else if (c == ';') {
                        state = State.TERMINATOR;
                    } else if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //region const
                case C:
                    if (c == 'o') {
                        state = State.CO;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;
                case CO:
                    if (c == 'n') {
                        state = State.CON;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;
                case CON:
                    if (c == 's') {
                        state = State.CONS;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;
                case CONS:
                    if (c == 't') {
                        state = State.CONST;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;
                case CONST:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                        back();
                        return Token.CREATE_CONST;
                    }
                    if (c == 't') {
                        state = State.CONST;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //endregion

                case IDENTIFIER:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                        back();
                        return Token.IDENTIFIER;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                case ATTRIB:
                    //TODO: if =, go to State.equal
                    state = State.INITIAL;
                    back();
                    return Token.OP_ATTRIB;

                //region char

                case SINGLE_QUOTE_START:
                    state = State.CHAR;
                    break;

                case CHAR:
                    if (c == '\'') {
                        state = State.SINGLE_QUOTE_END;
                    } else {
                        throw new LexicalException("Unexpected token: " + c);
                    }
                    break;

                case SINGLE_QUOTE_END:
                    state = State.INITIAL;
                    back();
                    return Token.LIT_CHAR;

                //endregion

                case TERMINATOR:
                    state = State.INITIAL;
                    back();
                    return Token.TERMINATOR;

                default:
                    throw new LexicalException("Invalid identifier: " + c);
            }
        }

        return null;
    }
}
