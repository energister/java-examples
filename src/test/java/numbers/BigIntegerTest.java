package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class BigIntegerTest {
    @Test
    public void convertFromLongAndBack() {
        BigInteger bi = BigInteger.valueOf(100L);

        Assertions.assertEquals(100L, bi.longValueExact());
    }
}
