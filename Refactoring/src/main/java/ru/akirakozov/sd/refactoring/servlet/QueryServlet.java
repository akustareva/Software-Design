package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.queries.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryServlet extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";
    private static final Map<String, Query> queryByType = new HashMap<String, Query>() {{
        put(OperationType.MAX.text, MaxQuery.getInstance());
        put(OperationType.MIN.text, MinQuery.getInstance());
        put(OperationType.SUM.text, SumQuery.getInstance());
        put(OperationType.COUNT.text, CountQuery.getInstance());
    }};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HtmlResponseBuilder responseBuilder = new HtmlResponseBuilder(response);
        String command = request.getParameter(COMMAND_PARAMETER);
        Query query = queryByType.get(command);
        if (query != null) {
            query.execute(responseBuilder);
        } else {
            responseBuilder.addLine("Unknown command: " + command);
        }
        responseBuilder.createResponse();
    }

    enum OperationType {
        MAX("max"),
        MIN("min"),
        SUM("sum"),
        COUNT("count");

        String text;
        OperationType(String text) {
            this.text = text;
        }
    }
}
