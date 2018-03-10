import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Anna on 09.03.2018.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RequestHandler handler = new RequestHandler(new PrintWriter(System.out));
        handler.getResponses(reader.readLine());
    }
}
