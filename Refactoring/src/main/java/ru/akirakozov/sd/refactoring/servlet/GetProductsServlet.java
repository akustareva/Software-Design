package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.Product;
import ru.akirakozov.sd.refactoring.database.SqlManager;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlManager sqlManager = SqlManager.getInstance();
        HtmlResponseBuilder responseBuilder = new HtmlResponseBuilder(response);
        List<Product> products = sqlManager.getAllProducts();
        for (Product product : products) {
            responseBuilder.addLine(product.getName() + "\t" + product.getPrice());
        }
        responseBuilder.createResponse();
    }
}
