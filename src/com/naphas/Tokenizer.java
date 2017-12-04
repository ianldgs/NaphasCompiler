package com.naphas;

import com.naphas.exceptions.LexicalException;

import java.util.HashMap;
import java.util.Map;

public class Tokenizer {
    private enum State {
        INITIAL,
        IDENTIFIER,
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
        INT,
        INT_DOT,
        FLOAT,
        START_ARRAY,
        END_ARRAY,
        SEPARATOR,
        START_EXP,
        END_EXP,
        START_BLOCK,
        END_BLOCK,
        PIPE,
        OR,
        AMPERSTAND,
        AND,
        EQUAL,
        IDENTICAL,
        NOT,
        NOT_EQUAL,
        DIFF,
        SINGLE_QUOTE_START,
        CHAR,
        SINGLE_QUOTE_END,
        DOUBLE_QUOTE_START,
        STRING,
        DOUBLE_QUOTE_END,
        TERMINATOR,
        SINGLE_LINE_COMMENT,
        MULTI_LINE_START_COMMENT,
        MULTI_LINE_COMMENT,
        MULTI_LINE_END_COMMENT1,
        MULTI_LINE_END_COMMENT2,
    }

    private final char END_CODE = '$';

    private int lastId = 0;
    private HashMap<Integer, Token> symbols = new HashMap<Integer, Token>();

    private String code;

    private State state = State.INITIAL;

    private int position = 0;
    private int line = 1;
    private int column = 1;

    private String lexeme;

    public Tokenizer(String code) {
        this.code = code + END_CODE;

        symbols.put(++lastId, new Token(lastId, Type.IF, "if"));
        symbols.put(++lastId, new Token(lastId, Type.ELSE, "else"));
        symbols.put(++lastId, new Token(lastId, Type.SWITCH, "switch"));
        symbols.put(++lastId, new Token(lastId, Type.CASE, "case"));
        symbols.put(++lastId, new Token(lastId, Type.FOR, "for"));
        symbols.put(++lastId, new Token(lastId, Type.WHILE, "while"));
        symbols.put(++lastId, new Token(lastId, Type.DO, "do"));
        symbols.put(++lastId, new Token(lastId, Type.CREATE_CONST, "const"));
        symbols.put(++lastId, new Token(lastId, Type.CREATE_VAR, "let"));
        symbols.put(++lastId, new Token(lastId, Type.TYPE_INT, "int"));
        symbols.put(++lastId, new Token(lastId, Type.TYPE_CHAR, "char"));
        symbols.put(++lastId, new Token(lastId, Type.TYPE_FLOAT, "float"));
        symbols.put(++lastId, new Token(lastId, Type.TYPE_STRING, "string"));
        symbols.put(++lastId, new Token(lastId, Type.TYPE_BOOLEAN, "boolean"));
        symbols.put(++lastId, new Token(lastId, Type.LIT_BOOLEAN_TRUE, "true"));
        symbols.put(++lastId, new Token(lastId, Type.LIT_BOOLEAN_FALSE, "false"));
        symbols.put(++lastId, new Token(lastId, Type.LIT_NULL, "null"));
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

    private void error(String message) throws LexicalException {
        state = State.INITIAL;
        throw new LexicalException(message + ". Near line " + line + " and column " + column);
    }

    private Token buildTokenAndRollBack(Type type) {
        state = State.INITIAL;
        back();

        int currentId = ++lastId;

        Token token = new Token(currentId, type, lexeme);

        if (type == Type.IDENTIFIER) {
            for (Map.Entry<Integer, Token> entry : symbols.entrySet()) {
                //Integer id = entry.getKey();
                Token _token = entry.getValue();

                if (token.getLexeme().equals(_token.getLexeme())) {
                    token = new Token(currentId, _token.getType(), lexeme);
                    break;
                }
            }
        }

        symbols.put(currentId, token);

        return token;
    }

    public Token getNextToken() throws LexicalException {
        lexeme = "";

        while (position < code.length()) {
            char c = getNextCharacter();

            column++;
            if (c == '\n') {
                line++;
                column = 1;
            }

            System.out.println("---------");
            System.out.println(state);
            System.out.println(c);

            lexeme += c;

            switch (state) {
                //region initial/identifier

                case INITIAL:
                    if (Character.isWhitespace(c)) {
                        state = State.INITIAL;
                    } else if (c == '=') {
                        state = State.ATTRIB;
                    } else if (c == '+') {
                        state = State.ADD;
                    } else if (c == '-') {
                        state = State.SUB;
                    } else if (c == '*') {
                        state = State.MULT;
                    } else if (c == '/') {
                        state = State.DIV;
                    } else if (c == '%') {
                        state = State.MOD;
                    } else if (c == '^') {
                        state = State.EXP;
                    } else if (c == '>') {
                        state = State.GT;
                    } else if (c == '<') {
                        state = State.LT;
                    } else if (c == '\'') {
                        state = State.SINGLE_QUOTE_START;
                    } else if (c == '"') {
                        state = State.DOUBLE_QUOTE_START;
                    } else if (c == ',') {
                        state = State.SEPARATOR;
                    } else if (c == '[') {
                        state = State.START_ARRAY;
                    } else if (c == ']') {
                        state = State.END_ARRAY;
                    } else if (c == '(') {
                        state = State.START_EXP;
                    } else if (c == ')') {
                        state = State.END_EXP;
                    } else if (c == '{') {
                        state = State.START_BLOCK;
                    } else if (c == '}') {
                        state = State.END_BLOCK;
                    } else if (c == '|') {
                        state = State.PIPE;
                    } else if (c == '&') {
                        state = State.AMPERSTAND;
                    } else if (c == '!') {
                        state = State.NOT;
                    } else if (c == ';') {
                        state = State.TERMINATOR;
                    } else if (Character.isLetter(c)) {
                        state = State.IDENTIFIER;
                    } else if (Character.isDigit(c)) {
                        state = State.INT;
                    } else {
                        if (position < code.length()) {
                            error("Unexpected: " + c);
                        }
                    }
                    break;

                case IDENTIFIER:
                    if (!Character.isLetterOrDigit(c)) {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.IDENTIFIER);
                    } else {
                        state = State.IDENTIFIER;
                    }
                    break;

                //endregion

                //region int

                case INT:
                    if (Character.isDigit(c)) {
                        state = State.INT;
                    } else if (Character.isWhitespace(c) || c == END_CODE) {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.LIT_INT);
                    } else if (c == '.') {
                        state = State.INT_DOT;
                    } else {
                        error("Unexpected: " + c);
                    }
                    break;

                //endregion

                //region float

                case INT_DOT:
                    if (Character.isDigit(c)) {
                        state = State.FLOAT;
                    } else {
                        error("Unexpected end of LIT_FLOAT");
                    }
                    break;

                case FLOAT:
                    if (Character.isDigit(c)) {
                        state = State.FLOAT;
                    } else if (Character.isWhitespace(c) || c == END_CODE) {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.LIT_FLOAT);
                    } else {
                        error("Unexpected: " + c);
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
                        back();
                        error("Unexpected: " + c);
                    }
                    break;

                case SINGLE_QUOTE_END:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.LIT_CHAR);

                //endregion

                //region string

                case DOUBLE_QUOTE_START:
                    state = State.STRING;
                    break;

                case STRING:
                    if (c == '"') {
                        state = State.DOUBLE_QUOTE_END;
                    } else if (c == END_CODE) {
                        error("Unexpected end of LIT_STRING");
                    } else {
                        state = State.STRING;
                    }
                    break;

                case DOUBLE_QUOTE_END:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.LIT_STRING);

                //endregion

                //region operators

                case ATTRIB:
                    if (c == '=') {
                        state = State.EQUAL;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_ATTRIB);
                    }
                    break;

                case ADD:
                    if (c == '=') {
                        state = State.ADD_SET;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_ADD);
                    }
                    break;

                case ADD_SET:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_ADD_SET);

                case SUB:
                    if (c == '=') {
                        state = State.SUB_SET;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_SUB);
                    }
                    break;

                case SUB_SET:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_SUB_SET);

                case MULT:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_MULT);

                case DIV:
                    if (c == '/') {
                        state = State.SINGLE_LINE_COMMENT;
                    } else if (c == '*') {
                        state = State.MULTI_LINE_START_COMMENT;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_DIV);
                    }
                    break;

                case MOD:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_MOD);

                case EXP:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_EXP);

                case GT:
                    if (c == '=') {
                        state = State.GTE;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_GT);
                    }
                    break;

                case GTE:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_GTE);

                case LT:
                    if (c == '=') {
                        state = State.LTE;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_LT);
                    }
                    break;

                case LTE:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_LTE);

                //endregion

                //region start/end

                case START_ARRAY:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.START_ARRAY);

                case END_ARRAY:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.END_ARRAY);

                case START_EXP:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.START_EXP);

                case END_EXP:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.END_EXP);

                case START_BLOCK:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.START_BLOCK);

                case END_BLOCK:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.END_BLOCK);

                case SEPARATOR:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.SEPARATOR);

                case TERMINATOR:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.TERMINATOR);

                //endregion

                //region logical operators

                case PIPE:
                    if (c == '|') {
                        state = State.OR;
                    } else {
                        error("Unexpected: " + c);
                    }
                    break;

                case OR:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_OR);

                case AMPERSTAND:
                    if (c == '&') {
                        state = State.AND;
                    } else {
                        error("Unexpected: " + c);
                    }
                    break;

                case AND:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_AND);

                case NOT:
                    if (c == '=') {
                        state = State.NOT_EQUAL;
                    } else {
                        state = State.INITIAL;
                        return buildTokenAndRollBack(Type.OP_NOT);
                    }
                    break;

                case NOT_EQUAL:
                    if (c == '=') {
                        state = State.DIFF;
                    } else {
                        error("Unexpected: " + c);
                    }
                    break;

                case DIFF:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_DIFF);

                case EQUAL:
                    if (c == '=') {
                        state = State.IDENTICAL;
                    } else {
                        error("Unexpected: " + c);
                    }
                    break;

                case IDENTICAL:
                    state = State.INITIAL;
                    return buildTokenAndRollBack(Type.OP_EQUAL);

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

                case MULTI_LINE_START_COMMENT:
                    if (c == '*') {
                        state = State.MULTI_LINE_END_COMMENT1;
                    } else {
                        state = State.MULTI_LINE_COMMENT;
                    }
                    break;

                case MULTI_LINE_COMMENT:
                    if (c == '*') {
                        state = State.MULTI_LINE_END_COMMENT1;
                    } else {
                        state = State.MULTI_LINE_COMMENT;
                    }
                    break;

                case MULTI_LINE_END_COMMENT1:
                    if (c == '/') {
                        state = State.MULTI_LINE_END_COMMENT2;
                    } else {
                        state = State.SINGLE_LINE_COMMENT;
                    }
                    break;

                case MULTI_LINE_END_COMMENT2:
                    state = State.INITIAL;
                    back();
                    lexeme = "";
                    break;

                //endregion

                default:
                    error("Unexpected: " + c);
            }
        }

        return null;
    }
}
