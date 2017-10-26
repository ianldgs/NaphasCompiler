import java.util.HashMap;
import java.util.Map;

public class Tokenizer {
    private enum State {
        INITIAL,
        IDENTIFIER,
        C,
        CO,
        CON,
        CONS,
        //TODO: Tabela de símbolos. Onde vai ficar?
        CONST,
        L,
        LE,
        //TODO: Tabela de símbolos. Onde vai ficar?
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

    private int lastId = 0;
    private HashMap<Integer, Token> symbols = new HashMap<Integer, Token>();

    private String code;

    private State state = State.INITIAL;

    private int position = 0;

    private String lexeme;

    public Tokenizer(String code) {
        this.code = code + "$";

        symbols.put(++lastId, new Token(Token.Type.IF, "if"));
        symbols.put(++lastId, new Token(Token.Type.ELSE, "else"));
        symbols.put(++lastId, new Token(Token.Type.SWITCH, "switch"));
        symbols.put(++lastId, new Token(Token.Type.CASE, "case"));
        symbols.put(++lastId, new Token(Token.Type.FOR, "for"));
        symbols.put(++lastId, new Token(Token.Type.WHILE, "while"));
        symbols.put(++lastId, new Token(Token.Type.DO, "do"));
        symbols.put(++lastId, new Token(Token.Type.CREATE_CONST, "const"));
        symbols.put(++lastId, new Token(Token.Type.CREATE_VAR, "let"));
        symbols.put(++lastId, new Token(Token.Type.TYPE_INT, "int"));
        symbols.put(++lastId, new Token(Token.Type.TYPE_CHAR, "char"));
        symbols.put(++lastId, new Token(Token.Type.TYPE_FLOAT, "float"));
        symbols.put(++lastId, new Token(Token.Type.TYPE_STRING, "string"));
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
        Token token = new Token(type, lexeme);

        if (type == Token.Type.IDENTIFIER) {
            for (Map.Entry<Integer, Token> entry : symbols.entrySet()) {
                //Integer id = entry.getKey();
                Token _token = entry.getValue();

                if (token.getLexeme().equals(_token.getLexeme())) {
                    token = new Token(_token.getType(), lexeme);
                    break;
                }
            }
        }

        symbols.put(++lastId, token);

        return token;
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
