package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryServletTest {
    private static final String COMMAND_PARAMETER = "command";
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void init() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void minTest() throws IOException {
        request.addParameter(COMMAND_PARAMETER, QueryServlet.OperationType.MIN.text);
        new QueryServlet().doGet(request, response);
        Assert.assertTrue(response.getStatus() == HttpServletResponse.SC_OK);
        Assert.assertEquals("text/html", response.getContentType());
        Assert.assertTrue(response.getContentAsString().contains("Product with min price:"));
    }

    @Test
    public void maxTest() throws IOException {
        request.addParameter(COMMAND_PARAMETER, QueryServlet.OperationType.MAX.text);
        new QueryServlet().doGet(request, response);
        Assert.assertTrue(response.getStatus() == HttpServletResponse.SC_OK);
        Assert.assertEquals("text/html", response.getContentType());
        Assert.assertTrue(response.getContentAsString().contains("Product with max price:"));
    }

    @Test
    public void sumTest() throws IOException {
        request.addParameter(COMMAND_PARAMETER, QueryServlet.OperationType.SUM.text);
        new QueryServlet().doGet(request, response);
        Assert.assertTrue(response.getStatus() == HttpServletResponse.SC_OK);
        Assert.assertEquals("text/html", response.getContentType());
        Assert.assertTrue(response.getContentAsString().contains("Summary price:"));
    }

    @Test
    public void countTest() throws IOException {
        request.addParameter(COMMAND_PARAMETER, QueryServlet.OperationType.COUNT.text);
        new QueryServlet().doGet(request, response);
        Assert.assertTrue(response.getStatus() == HttpServletResponse.SC_OK);
        Assert.assertEquals("text/html", response.getContentType());
        Assert.assertTrue(response.getContentAsString().contains("Number of products:"));
    }

    @Test
    public void unknownCommandTest() throws IOException {
        request.addParameter(COMMAND_PARAMETER, "smth");
        new QueryServlet().doGet(request, response);
        Assert.assertTrue(response.getStatus() == HttpServletResponse.SC_OK);
        Assert.assertEquals("text/html", response.getContentType());
        Assert.assertTrue(response.getContentAsString().contains("Unknown command:"));
    }
}
