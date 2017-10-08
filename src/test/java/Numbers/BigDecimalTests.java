package Numbers;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalTests {
    @Test
    public void scaleAndPrecision() {
        // Arrange
        BigDecimal bd = new BigDecimal("1.989");

        // Assert
        /*
           Precision:
            * the number of digits in the unscaled value.
            * (Total number of significant digits)
         */
        assertEquals(4, bd.precision());

        /*
            Scale:
            Number of digits to the right of the decimal point (if zero or positive)
        */
        assertEquals(3, bd.scale());
    }

    @Test
    public void representation() {
        // Arrange
        BigDecimal bd = new BigDecimal("1E+1");

        assertEquals("10", bd.toPlainString());
    }
}
