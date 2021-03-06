package deepdive.functional.java;

import java.util.function.BiFunction;

@SuppressWarnings("unused")
public class FistClassCitizen {

    // Inline function as a part of the class.
    int add(int x, int y) {
        return x + y;
    }

    // Functions can also be stored in normal variables, like other data types.
    // The data type is `BiFunction` with two input and a return type parameter.

    // type    type parameter             name   input vector
    BiFunction<Integer, Integer, Integer> fAdd = (x, y) -> {
        return x + y;       // <-- function body
    };

    // For one line functions, we can also use this short syntax:
    BiFunction<Integer, Integer, Integer> fAddSugar = (x, y) -> x + y;


    {
        add(1, 2);
        fAdd.apply(1, 2);           // To call the function, we have to call explicit the `apply` method in Java. :-(
        fAddSugar.apply(1, 2);
    }
}
