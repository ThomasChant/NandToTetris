/**
 * Created by chentao on 2016/10/18 0018.
 */
public enum  Mark {
    AT("@"),
    EQUAL("="),
    SEMICOLON(";"),
    SLASH("//"),
    LEFT_PARENTHESE("("),
    RIGHT_PARENTHESE(")");
    private String value;

    Mark(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
