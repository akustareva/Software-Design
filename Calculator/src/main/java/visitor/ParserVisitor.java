package visitor;

import token.Bracket;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private List<Token> result = new ArrayList<>();
    private Stack<Token> stack = new Stack<>();

    @Override
    public void visit(NumberToken token) {
        result.add(token);
    }

    @Override
    public void visit(Bracket token) {
        if (token.getTokenType() == Token.TokenType.LEFT) {
            stack.add(token);
        } else {
            while (!stack.isEmpty() && stack.peek().getTokenType() != Token.TokenType.LEFT) {
                result.add(stack.pop());
            }
            if (stack.isEmpty()) {
                throw new IllegalStateException("Unexpected end of stack");
            }
            stack.pop();
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            Token operation = stack.peek();
            if (!(operation instanceof Operation)) {
                break;
            }
            if (getOperationPriority((Operation) operation) >= getOperationPriority(token)) {
                result.add(operation);
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }

    public List<Token> transformToReversePolishNotation(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        while (!stack.isEmpty()) {
            Token element = stack.peek();
            stack.pop();
            if (element instanceof Bracket) {
                throw new IllegalStateException("Incorrect input expression: mismatched brackets");
            }
            result.add(element);
        }
        return result;
    }

    private int getOperationPriority(Operation token) {
        if (token.getTokenType() == Token.TokenType.ADD || token.getTokenType() == Token.TokenType.SUB) {
            return 0;
        }
        if (token.getTokenType() == Token.TokenType.MUL || token.getTokenType() == Token.TokenType.DIV)  {
            return 1;
        }
        throw new IllegalArgumentException("Unknown operation type");
    }
}
