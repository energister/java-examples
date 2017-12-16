package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberLiteralsTest {
    @Test
    public void defaultTypeIsDouble() {
        double d = 3.5; // OK

        /*
        float f = 3.5; // Won't compile: incompatible types: possible lossy conversion from double to float
         */
    }

    @Test
    public void beWarned() {
        assertEquals("0.8999999999999999", String.valueOf(2.0 - 1.1));
    }
}
