package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.database.SqlManager;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

public class SumQuery implements Query {
    private static SumQuery instance;

    private SumQuery() {}

    public static SumQuery getInstance() {
        if (instance == null) {
            instance = new SumQuery();
        }
        return instance;
    }

    @Override
    public void execute(HtmlResponseBuilder responseBuilder) {
        int pricesSum = SqlManager.getInstance().getPricesSum();
        responseBuilder.addLine("Summary price: " + pricesSum);
    }
}
