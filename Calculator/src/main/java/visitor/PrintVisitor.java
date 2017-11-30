package visitor;

import token.Bracket;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.ArrayList;
import java.util.List;

public class PrintVisitor implements TokenVisitor {
    private List<String> result = new ArrayList<>();

    @Override
    public void visit(NumberToken token) {
        result.add(token.toString());
    }

    @Override
    public void visit(Bracket token) {
        result.add(token.toString());
    }

    @Override
    public void visit(Operation token) {
        result.add(token.toString());
    }

    public String transformToString(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        return String.join(" ", result);
    }
}
