import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    private final String CODE_CONST_ATTRIB = "const a = 'b';";

    @Test
    void testTokenConstAttrib() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_CONST_ATTRIB);

        assertEquals(Token.Type.CREATE_CONST, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.IDENTIFIER, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.OP_ATTRIB, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.LIT_CHAR, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.TERMINATOR, tokenizer.getNextToken().getType());
    }

    @Test
    void testLexemeConstAttrib() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_CONST_ATTRIB);

        assertEquals("const", tokenizer.getNextToken().getLexeme());
        assertEquals("a", tokenizer.getNextToken().getLexeme());
        assertEquals("=", tokenizer.getNextToken().getLexeme());
        assertEquals("'b'", tokenizer.getNextToken().getLexeme());
        assertEquals(";", tokenizer.getNextToken().getLexeme());
    }
}