import token.Token;
import token.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Specify the input filename.");
            return;
        }
        String expression = readFile(args[0]);
        List<Token> tokens = new Tokenizer().tokenize(expression);
        System.out.println(new PrintVisitor().transformToString(tokens));
        List<Token> reversedTokens = new ParserVisitor().transformToReversePolishNotation(tokens);
        System.out.println(new PrintVisitor().transformToString(reversedTokens));
        System.out.println(new CalcVisitor().calculate(reversedTokens));
    }

    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String expression = reader.readLine();
        reader.close();
        return expression;
    }
}
