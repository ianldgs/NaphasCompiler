public class Token {


    private int id;

    private Type type;

    private String lexeme;

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Token(int id, Type type, String lexeme) {
        this.id = id;
        this.type = type;
        this.lexeme = lexeme.trim();
    }

    @Override
    public String toString() {
        return "<" + id + ", " + type + ", " + lexeme + ">";
    }
}