package ru.akirakozov.sd.refactoring.html;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlResponseBuilder {
    private List<String> headers;
    private List<String> lines;
    private HttpServletResponse response;

    public HtmlResponseBuilder(HttpServletResponse response) {
        headers = new ArrayList<>();
        lines = new ArrayList<>();
        this.response = response;
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void createResponse() {
        try {
            response.getWriter().println("<html><body>");
            for (String header : headers) {
                response.getWriter().println("<h1>" + header + "</h1>");
            }
            for (String line : lines) {
                response.getWriter().println(line + "</br>");
            }
            response.getWriter().println("</body></html>");
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
