import java.util.HashMap;

public class Tokenizer {
    private enum State {
        INITIAL,
        IDENTIFIER,
        C,
        CO,
        CON,
        CONS,
        CONST,
        L,
        LE,
        LET,
        ATTRIB,
        SINGLE_QUOTE_START,
        CHAR,
        SINGLE_QUOTE_END,
        DOUBLE_QUOTE_START,
        STRING,
        DOUBLE_QUOTE_END,
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
        lexeme = lexeme.substring(0, lexeme.length() - n);
    }

    public Token getNextToken() throws Exception {
        lexeme = "";

        while (position < code.length()) {
            char c = getNextCharacter();

            System.out.println("---------");
            System.out.println(state);
            System.out.println(c);

            lexeme += c;

            switch (state) {
                case INITIAL:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                    } else if (c == 'c') {
                        state = State.C;
                    } else if (c == 'l') {
                        state = State.L;
                    } else if (c == '=') {
                        state = State.ATTRIB;
                    } else if (c == '\'') {
                        state = State.SINGLE_QUOTE_START;
                    } else if (c == '"') {
                        state = State.DOUBLE_QUOTE_START;
                    } else if (c == ';') {
                        state = State.TERMINATOR;
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //region const

                case C:
                    if (c == 'o') {
                        state = State.CO;
                    } else {
                        back();
                        state = State.IDENTIFIER;
                    }
                    break;
                case CO:
                    if (c == 'n') {
                        state = State.CON;
                    } else {
                        back();
                        state = State.IDENTIFIER;
                    }
                    break;
                case CON:
                    if (c == 's') {
                        state = State.CONS;
                    } else {
                        back();
                        state = State.IDENTIFIER;
                    }
                    break;
                case CONS:
                    if (c == 't') {
                        state = State.CONST;
                    } else {
                        back();
                        state = State.IDENTIFIER;
                    }
                    break;
                case CONST:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                        back();
                        return new Token(Token.Type.CREATE_CONST, lexeme);
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //endregion

                //region let

                case L:
                    if (c == 'e') {
                        state = State.LE;
                    } else {
                        back();
                        state = State.IDENTIFIER;
                    }
                    break;
                case LE:
                    if (c == 't') {
                        state = State.LET;
                    } else {
                        back();
                        state = State.IDENTIFIER;
                    }
                    break;
                case LET:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                        back();
                        return new Token(Token.Type.CREATE_VAR, lexeme);
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //endregion

                case IDENTIFIER:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                        back();
                        return new Token(Token.Type.IDENTIFIER, lexeme);
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                case ATTRIB:
                    //TODO: if =, go to State.equal
                    state = State.INITIAL;
                    back();
                    return new Token(Token.Type.OP_ATTRIB, lexeme);

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
                    return new Token(Token.Type.LIT_CHAR, lexeme);

                //endregion

                //region string

                case DOUBLE_QUOTE_START:
                    state = State.STRING;
                    break;

                case STRING:
                    if (c == '"') {
                        state = State.DOUBLE_QUOTE_END;
                    } else {
                        state = State.STRING;
                    }
                    break;

                case DOUBLE_QUOTE_END:
                    state = State.INITIAL;
                    back();
                    return new Token(Token.Type.LIT_STRING, lexeme);

                //endregion

                case TERMINATOR:
                    state = State.INITIAL;
                    back();
                    return new Token(Token.Type.TERMINATOR, lexeme);

                default:
                    throw new LexicalException("Invalid identifier: " + c);
            }
        }

        return null;
    }
}
