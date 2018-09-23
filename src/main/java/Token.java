/**
 * This class is used to represent the token.
 */
public class Token {
    private TokenType type; // token type
    private String value; // the value of the token

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Type: " + this.type + " value: " + this.value + "";
    }
}
