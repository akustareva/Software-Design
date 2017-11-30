package token;

import visitor.TokenVisitor;

public class NumberToken implements Token {
    private int value;

    public NumberToken(String s) {
        value = Integer.parseInt(s);
    }

    public int getValue() {
        return value;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.NUM;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NUMBER(" + value + ")";
    }
}
