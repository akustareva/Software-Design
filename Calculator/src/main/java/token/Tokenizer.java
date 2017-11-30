package token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public List<Token> tokenize(String expression) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            while (isWhitespaceCharacter(c)) {
                i++;
                c = expression.charAt(i);
            }
            if (isBracket(c)) {
                tokens.add(new Bracket(c));
            } else if (isOperation(c)) {
                tokens.add(new Operation(c));
            } else if (Character.isDigit(c)) {
                String num = String.valueOf(c);
                int pos = i + 1;
                while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
                    num += String.valueOf(expression.charAt(pos));
                    pos++;
                }
                i = pos - 1;
                tokens.add(new NumberToken(num));
            } else {
                throw new IllegalStateException("Couldn't parse expression at position " + i);
            }
        }
        return tokens;
    }

    private boolean isWhitespaceCharacter(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private boolean isBracket(char c) {
        return c == '(' || c == ')';
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
