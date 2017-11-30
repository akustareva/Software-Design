package token;

import visitor.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
    TokenType getTokenType();

    enum TokenType {
        NUM("NUMBER"),
        ADD("PLUS"),
        SUB("MINUS"),
        MUL("MUL"),
        DIV("DIV"),
        LEFT("LEFT"),
        RIGHT("RIGHT");

        private String str;

        TokenType(String str) {
            this.str = str;
        }

        static TokenType getTokenTypeByChar(char c) {
            switch (c) {
                case '+':
                    return ADD;
                case '-':
                    return SUB;
                case '*':
                    return MUL;
                case '/':
                    return DIV;
                case '(':
                    return LEFT;
                case ')':
                    return RIGHT;
                default:
                    throw new IllegalArgumentException("Unexpected token");
            }
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
