package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.Product;
import ru.akirakozov.sd.refactoring.database.SqlManager;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

public class MaxQuery implements Query {
    private static MaxQuery instance;

    private MaxQuery() {}

    public static MaxQuery getInstance() {
        if (instance == null) {
            instance = new MaxQuery();
        }
        return instance;
    }

    @Override
    public void execute(HtmlResponseBuilder responseBuilder) {
        Product product = SqlManager.getInstance().getMostExpensiveProduct();
        responseBuilder.addHeader("Product with max price:");
        responseBuilder.addLine(product.getName() + "\t" + product.getPrice());
    }
}
