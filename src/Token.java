public class Token {
    public enum Type {
        IDENTIFIER,
        LIT_INT,
        LIT_FLOAT,
        LIT_CHAR,
        LIT_STRING,
        LIT_BOOLEAN_TRUE,
        LIT_BOOLEAN_FALSE,
        TYPE_INT,
        TYPE_FLOAT,
        TYPE_CHAR,
        TYPE_STRING,
        START_ARRAY,
        END_ARRAY,
        CREATE_VAR,
        CREATE_CONST,
        SEPARATOR,
        OP_ATTRIB,
        OP_ADD,
        OP_SUB,
        OP_MULT,
        OP_DIV,
        OP_MOD,
        OP_ADD_SET,
        OP_SUB_SET,
        OP_EXP,
        OP_GT,
        OP_GTE,
        OP_LT,
        OP_LTE,
        START_EXP,
        END_EXP,
        TERMINATOR,
        OP_OR,
        OP_AND,
        OP_EQUAL,
        OP_DIFF,
        OP_NOT,
        START_BLOCK,
        END_BLOCK,
        IF,
        ELSE,
        SWITCH,
        CASE,
        FOR,
        WHILE,
        DO,
        //TODO: ver se vai precisar disso, ou se vai simplesmente voltar pro estado inicial quando tiver comentário
        START_COMMENT,
        START_BLOCK_COMMENT,
        END_BLOCK_COMMENT,
        //TODO: é realmente Token? ou vai ser um ID já cadastrado na tabela de símbolos?
        OP_INPUT,
        OP_OUTPUT,
    }

    private Type type;

    private String lexeme;

    public Type getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Token(Type type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme.trim();
    }
}