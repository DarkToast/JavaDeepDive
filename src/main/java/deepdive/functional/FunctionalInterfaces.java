package deepdive.functional;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaces {

    Function<Integer, Integer> doubleX = (x) -> x * x;

    BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;

    Predicate<Integer> test = (x) -> x == 2;

    // A `functional`-interface for side effects. Strictly speaking, that is not a function.
    Consumer<Integer> sideEffect = (x) -> System.out.println(x);



    // --- My own function interface
    // Every interface with one method, can be used as functional interface.

    interface ToStringer<T> {
        String makeString(T value);
    }

    ToStringer<Integer> myToString = (x) -> x.toString();   // <-- Implements `makeString`.

}
