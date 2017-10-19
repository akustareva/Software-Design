package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.Product;
import ru.akirakozov.sd.refactoring.database.SqlManager;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

public class MinQuery implements Query {
    private static MinQuery instance;

    private MinQuery() {}

    public static MinQuery getInstance() {
        if (instance == null) {
            instance = new MinQuery();
        }
        return instance;
    }

    @Override
    public void execute(HtmlResponseBuilder responseBuilder) {
        Product product = SqlManager.getInstance().getCheapestProduct();
        responseBuilder.addHeader("Product with min price:");
        responseBuilder.addLine(product.getName() + "\t" + product.getPrice());
    }
}
