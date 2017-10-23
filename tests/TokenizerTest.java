import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    //region const attrib

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

    //endregion

    //region const bogus

    private final String CODE_CONST_BOGUS = "constt a = 'b';";

    @Test
    void testTokenConstBogus() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_CONST_BOGUS);

        assertEquals(Token.Type.IDENTIFIER, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.IDENTIFIER, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.OP_ATTRIB, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.LIT_CHAR, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.TERMINATOR, tokenizer.getNextToken().getType());
    }

    @Test
    void testLexemeConstBogus() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_CONST_BOGUS);

        assertEquals("constt", tokenizer.getNextToken().getLexeme());
        assertEquals("a", tokenizer.getNextToken().getLexeme());
        assertEquals("=", tokenizer.getNextToken().getLexeme());
        assertEquals("'b'", tokenizer.getNextToken().getLexeme());
        assertEquals(";", tokenizer.getNextToken().getLexeme());
    }

    //endregion

    //region let attrib

    private final String CODE_LET_ATTRIB = "let name = \"ian luca\";";

    @Test
    void testTokenLetAttrib() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_LET_ATTRIB);

        assertEquals(Token.Type.CREATE_VAR, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.IDENTIFIER, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.OP_ATTRIB, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.LIT_STRING, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.TERMINATOR, tokenizer.getNextToken().getType());
    }

    @Test
    void testLexemeLetAttrib() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_LET_ATTRIB);

        assertEquals("let", tokenizer.getNextToken().getLexeme());
        assertEquals("name", tokenizer.getNextToken().getLexeme());
        assertEquals("=", tokenizer.getNextToken().getLexeme());
        assertEquals("\"ian luca\"", tokenizer.getNextToken().getLexeme());
        assertEquals(";", tokenizer.getNextToken().getLexeme());
    }

    //endregion

    //region let bogus

    private final String CODE_LET_BOGUS = "le name = \"ian luca\";";

    @Test
    void testTokenLetBogus() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_LET_BOGUS);

        assertEquals(Token.Type.IDENTIFIER, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.IDENTIFIER, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.OP_ATTRIB, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.LIT_STRING, tokenizer.getNextToken().getType());
        assertEquals(Token.Type.TERMINATOR, tokenizer.getNextToken().getType());
    }

    @Test
    void testLexemeLetBogus() throws Exception {
        Tokenizer tokenizer = new Tokenizer(CODE_LET_BOGUS);

        assertEquals("le", tokenizer.getNextToken().getLexeme());
        assertEquals("name", tokenizer.getNextToken().getLexeme());
        assertEquals("=", tokenizer.getNextToken().getLexeme());
        assertEquals("\"ian luca\"", tokenizer.getNextToken().getLexeme());
        assertEquals(";", tokenizer.getNextToken().getLexeme());
    }

    //endregion
}