import entities.Result;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import seachers.GoogleSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Anna on 10.03.2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Jsoup.class })
public class SearcherTest {

    @Test
    public void correctnessTest() throws IOException {
        String query = "query";
        Connection connection = Mockito.mock(Connection.class);
        Document document = Mockito.mock(Document.class);
        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
        when(connection.userAgent(Mockito.anyString())).thenReturn(connection);
        when(connection.get()).thenReturn(document);
        when(document.select(Mockito.anyString())).thenReturn(createAnswer());

        List<Result> results = new GoogleSearcher(query).search();
        Assert.assertEquals(1, results.size());
        for (Result result: results) {
            Assert.assertNotNull(result.getUrl());
        }
    }

    private Elements createAnswer() {
        List<Element> elements = new ArrayList<>();
        Attributes a1 = new Attributes();
        a1.put("href", "/url?q=https://www.thesun.co.uk/&amp;sa=U&amp;ved=0ahUKEwi-g4_iq-HZAhUoQpoKHdbdDOwQFggUMAA&amp;usg=AOvVaw0G_GvvxYCIEy3aB38SQeZ3");
        a1.put("target", "_blank");
        Element e1 = new Element(Tag.valueOf("a"),
                "http://www.google.ru/search?q=sun&num=10&gws_rd=cr&dcr=0&ei=4ZejWrnuDue56AT265TQCw", a1);
        elements.add(e1);
        return new Elements(elements);
    }
}
