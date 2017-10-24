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
        ADD,
        ADD_SET,
        SUB,
        SUB_SET,
        MULT,
        DIV,
        MOD,
        EXP,
        GT,
        GTE,
        LT,
        LTE,
        SINGLE_QUOTE_START,
        CHAR,
        SINGLE_QUOTE_END,
        DOUBLE_QUOTE_START,
        STRING,
        DOUBLE_QUOTE_END,
        TERMINATOR,
        SINGLE_LINE_COMMENT,
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

    private Token buildTokenAndRollBack(Token.Type type) {
        back();
        return new Token(type, lexeme);
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
                //region initial/identifier

                case INITIAL:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                    }
                    else if (c == 'c') {
                        state = State.C;
                    }
                    else if (c == 'l') {
                        state = State.L;
                    }
                    else if (c == '=') {
                        state = State.ATTRIB;
                    }
                    else if (c == '+') {
                        state = State.ADD;
                    }
                    else if (c == '-') {
                        state = State.SUB;
                    }
                    else if (c == '*') {
                        state = State.MULT;
                    }
                    else if (c == '/') {
                        state = State.DIV;
                    }
                    else if (c == '%') {
                        state = State.MOD;
                    }
                    else if (c == '^') {
                        state = State.EXP;
                    }
                    else if (c == '>') {
                        state = State.GT;
                    }
                    else if (c == '<') {
                        state = State.LT;
                    }
                    else if (c == '\'') {
                        state = State.SINGLE_QUOTE_START;
                    }
                    else if (c == '"') {
                        state = State.DOUBLE_QUOTE_START;
                    }
                    else if (c == ';') {
                        state = State.TERMINATOR;
                    }
                    else if (Character.isLetter(c)) {
                        state = State.IDENTIFIER;
                    }
                    else {
                        throw new LexicalException("Unexpected: " + c);
                    }
                    break;

                case IDENTIFIER:
                    if (!Character.isLetterOrDigit(c)) {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Token.Type.IDENTIFIER);
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //endregion

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
                        return buildTokenAndRollBack(Token.Type.CREATE_CONST);
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
                        return buildTokenAndRollBack(Token.Type.CREATE_VAR);
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //endregion

                //region char

                case SINGLE_QUOTE_START:
                    state = State.CHAR;
                    break;

                case CHAR:
                    if (c == '\'') {
                        state = State.SINGLE_QUOTE_END;
                    } else {
                        throw new LexicalException("Unexpected: " + c);
                    }
                    break;

                case SINGLE_QUOTE_END:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.LIT_CHAR);

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
                    return buildTokenAndRollBack(Token.Type.LIT_STRING);

                //endregion

                //region operators

                case ATTRIB:
                    //TODO: if =, go to State.EQUAL
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_ATTRIB);

                case ADD:
                    if (c == '=') {
                        state = State.ADD_SET;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Token.Type.OP_ADD);
                    }
                    break;

                case ADD_SET:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_ADD_SET);

                case SUB:
                    if (c == '=') {
                        state = State.SUB_SET;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Token.Type.OP_SUB);
                    }
                    break;

                case SUB_SET:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_SUB_SET);

                case MULT:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_MULT);

                case DIV:
                    if (c == '/') {
                        state = State.SINGLE_LINE_COMMENT;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Token.Type.OP_DIV);
                    }
                    break;

                case MOD:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_MOD);

                case EXP:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_EXP);

                case GT:
                    if (c == '=') {
                        state = State.GTE;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Token.Type.OP_GT);
                    }
                    break;

                case GTE:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_GTE);

                case LT:
                    if (c == '=') {
                        state = State.LTE;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Token.Type.OP_LT);
                    }
                    break;

                case LTE:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.OP_LTE);

                case TERMINATOR:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Token.Type.TERMINATOR);

                //endregion

                //region comments

                case SINGLE_LINE_COMMENT:
                    if (c == '\n') {
                        state = State.INITIAL;
                        lexeme = "";
                    } else {
                        state = State.SINGLE_LINE_COMMENT;
                    }
                    break;

                //endregion

                default:
                    throw new LexicalException("Unexpected: " + c);
            }
        }

        return null;
    }
}
