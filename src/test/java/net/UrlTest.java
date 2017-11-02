package net;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class UrlTest {
    private enum Status { CREATED, PROCESSED }

    @Test
    public void buildUrlWithParameters() throws URISyntaxException {
        // Arrange
        URIBuilder builder = new URIBuilder("http://example.com/");

        // Act
        builder.addParameter("number", String.valueOf(5));
        builder.addParameter("enum", Status.CREATED.toString());
        builder.addParameter("paramWithSpace", "some text: and colon");

        // Assert
        assertEquals("http://example.com/?number=5&enum=CREATED&paramWithSpace=some+text%3A+and+colon", builder.toString());
    }
}
