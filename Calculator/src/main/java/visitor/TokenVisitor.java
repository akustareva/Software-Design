package visitor;

import token.Bracket;
import token.NumberToken;
import token.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Bracket token);
    void visit(Operation token);
}
