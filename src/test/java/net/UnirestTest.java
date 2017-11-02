package net;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnirestTest {
    @Test
    public void getWithParameters() throws UnirestException {
        // Arrange
        HttpRequest httpRequest = Unirest.get("http://httpbin.org/get")
                .queryString("parameter1", "123")
                .queryString("parameter2", "some text: with colon");

        // Act
        HttpResponse<JsonNode> result = httpRequest.asJson();

        // Assert
        int status = result.getStatus();
        assertEquals(HttpStatus.SC_OK, status);

        String statusText = result.getStatusText();
        assertEquals("OK", statusText);

        JsonNode body = result.getBody();
        assertEquals("http://httpbin.org/get?parameter1=123&parameter2=some+text%3A+with+colon",
                     body.getObject().getString("url"));
    }

    @Test
    public void getWithParametersInUrl() throws UnirestException {
        // Arrange
        HttpRequest httpRequest = Unirest.get("http://httpbin.org/get?parameter1=123");

        // Act
        HttpResponse<JsonNode> result = httpRequest.asJson();

        // Assert
        int status = result.getStatus();
        assertEquals(HttpStatus.SC_OK, status);

        String statusText = result.getStatusText();
        assertEquals("OK", statusText);

        JsonNode body = result.getBody();
        assertEquals("http://httpbin.org/get?parameter1=123",
                     body.getObject().getString("url"));
    }

    @Test
    public void unknownHost() {
        // Arrange
        HttpRequest httpRequest = Unirest.get("http://someNonExistingDomain.com");

        // Act
        UnirestException exception = assertThrows(UnirestException.class, () -> httpRequest.asString());

        // Assert
        Throwable cause = exception.getCause();
        assertThat(cause, instanceOf(UnknownHostException.class));

        UnknownHostException unknownHost = (UnknownHostException) cause;
        assertEquals("someNonExistingDomain.com", unknownHost.getMessage());
    }

    @Test
    public void asStringNotFound404() throws UnirestException {
        // Arrange
        HttpRequest httpRequest = Unirest.get("http://httpbin.org/someNonExistingEndpoint");

        // Act
        HttpResponse<String> stringHttpResponse = httpRequest.asString();

        // Assert
        assertEquals(HttpStatus.SC_NOT_FOUND, stringHttpResponse.getStatus());
        assertEquals("NOT FOUND", stringHttpResponse.getStatusText());

        String body = stringHttpResponse.getBody();
        assertThat(body, startsWith("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">"));
    }

    @Test
    public void asJsonNotFound404() {
        // Arrange
        HttpRequest httpRequest = Unirest.get("http://httpbin.org/someNonExistingEndpoint");

        // Act
        UnirestException exception = assertThrows(UnirestException.class, () -> httpRequest.asJson());

        // Assert
        assertThat(exception.getMessage(),
                   startsWith("java.lang.RuntimeException: java.lang.RuntimeException: org.json.JSONException: A JSONArray text must start with '[' at 1 [character 2 line 1]"));
    }
}
