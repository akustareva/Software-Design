package token;

import visitor.TokenVisitor;

public class Bracket implements Token {
    private TokenType tokenType;

    public Bracket(char c) {
        tokenType = Token.TokenType.getTokenTypeByChar(c);
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return tokenType.toString();
    }
}
