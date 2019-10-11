package api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.Closeable;
import java.io.IOException;

public class TestTimeout {
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @Test(timeOut = 1000)
    public void testTooSlow() throws IOException {
        response = client.execute(new HttpGet("https://api.github.com"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
}
