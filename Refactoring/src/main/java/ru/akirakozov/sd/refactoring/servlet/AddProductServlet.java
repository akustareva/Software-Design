package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.SqlManager;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    private static final String NAME_PARAMETER = "name";
    private static final String PRICE_PARAMETER = "price";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlManager sqlManager = SqlManager.getInstance();
        HtmlResponseBuilder responseBuilder = new HtmlResponseBuilder(response);
        String name = request.getParameter(NAME_PARAMETER);
        long price = Long.parseLong(request.getParameter(PRICE_PARAMETER));
        sqlManager.addProduct(name, price);
        responseBuilder.addLine("OK");
        responseBuilder.createResponse();
    }
}
