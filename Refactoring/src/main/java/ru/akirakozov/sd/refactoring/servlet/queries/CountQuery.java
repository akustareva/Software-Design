package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.SqlManager;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

public class CountQuery implements Query {
    private static CountQuery instance;

    private CountQuery() {}

    public static CountQuery getInstance() {
        if (instance == null) {
            instance = new CountQuery();
        }
        return instance;
    }

    @Override
    public void execute(HtmlResponseBuilder responseBuilder) {
        int productsCount = SqlManager.getInstance().getProductsCount();
        responseBuilder.addLine("Number of products: " + productsCount);
    }
}
