public enum Token {
    IDENTIFIER,
    NUM_DEC,
    NUM_OCT,
    NUM_HEX,
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
    START_COMMENT,
    START_BLOCK_COMMENT,
    END_BLOCK_COMMENT,
    //TODO: é realmente Token? ou vai ser um ID já cadastrado na tabela de símbolos?
    OP_INPUT,
    OP_OUTPUT,
}
