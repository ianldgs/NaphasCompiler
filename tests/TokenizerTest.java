import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test
    void testConstAttrib() throws Exception {
        Tokenizer tokenizer = new Tokenizer("const a = 'b';");

        assertEquals(Token.CREATE_CONST, tokenizer.getNextToken());
        assertEquals(Token.IDENTIFIER, tokenizer.getNextToken());
        assertEquals(Token.OP_ATTRIB, tokenizer.getNextToken());
        assertEquals(Token.LIT_CHAR, tokenizer.getNextToken());
        assertEquals(Token.TERMINATOR, tokenizer.getNextToken());
    }
}