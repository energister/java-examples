package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamTest {
    private static final List<Integer> list = Arrays.asList(1, 2, 3);

    @Test
    public void intStream() {
        IntStream intStream = list.stream()
                .mapToInt(Integer::intValue);

        OptionalInt max = intStream.max();

        int maxInt = max.orElse(0);
    }

    @Test
    public void streamOfInteger() {
        Stream<Integer> stream = list.stream();

        Optional<Integer> max = stream.max(Comparator.<Integer>naturalOrder());

        Integer maxInteger = max.orElse(0);
    }
}
