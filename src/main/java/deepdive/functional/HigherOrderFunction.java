package deepdive.functional;

import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class HigherOrderFunction {

    // A function, that returns a function.
    // Integer -> Integer -> Boolean
    Function<Integer, Boolean> greaterThan(Integer x) {
        return y -> y > x;
    }

    // A function, that takes a function.
    // (Integer, Integer -> T) -> T
    <T> T mapX(Integer x, Function<Integer, T> f) {
        return f.apply(x);
    }

    {
        // We will see this pattern later to be used for currying.
        Function<Integer, Boolean> f = greaterThan(10);
        boolean b = f.apply(5);

        mapX(10, x -> x.toString());

        // Streams are using high order functions:
        Stream<String> str = Stream.of(1, 2, 3).map(i -> i.toString());
    }
}

