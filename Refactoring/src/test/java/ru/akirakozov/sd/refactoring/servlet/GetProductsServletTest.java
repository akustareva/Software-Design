package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetProductsServletTest {

    @Test
    public void getAllProductsTests() throws IOException {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        new GetProductsServlet().doGet(request, response);
        Assert.assertTrue(response.getStatus() == HttpServletResponse.SC_OK);
        Assert.assertEquals("text/html", response.getContentType());
    }
}
