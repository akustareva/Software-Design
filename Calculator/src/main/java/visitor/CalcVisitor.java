package visitor;

import token.Bracket;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.List;
import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private Stack<Integer> stack = new Stack<>();

    @Override
    public void visit(NumberToken token) {
        stack.add(token.getValue());
    }

    @Override
    public void visit(Bracket token) {}

    @Override
    public void visit(Operation token) {
        int a = stack.pop();
        int b = stack.pop();
        if (token.getTokenType() == Token.TokenType.ADD) {
            stack.add(a + b);
        } else if (token.getTokenType() == Token.TokenType.SUB) {
            stack.add(b - a);
        } else if (token.getTokenType() == Token.TokenType.MUL) {
            stack.add(a * b);
        } else if (token.getTokenType() == Token.TokenType.DIV) {
            stack.add(b / a);
        }
    }

    public int calculate(List<Token> tokens) {
        for (Token token: tokens) {
            token.accept(this);
        }
        return stack.pop();
    }
}
