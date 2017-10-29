package strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringBuilderWithLnTests {
    @Test
    public void test() {
        // Arrange
        org.apache.commons.text.StrBuilder stringBuilder = new org.apache.commons.text.StrBuilder();

        // Act
        stringBuilder.appendln("Hello!");

        // Assert
        assertNotEquals("Hello!\n", stringBuilder.toString());
        assertEquals("Hello!" + System.lineSeparator(), stringBuilder.toString());
        assertEquals("\r\n", System.lineSeparator());
    }
}
