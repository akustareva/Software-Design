package ru.akirakozov.sd.refactoring.servlet.queries;

import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

public interface Query {
    void execute(HtmlResponseBuilder responseBuilder);
}
