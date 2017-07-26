package deepdive.typesystem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class NullAndVoid {
    
    public static void main(String[] args) {
        new NullAndVoid();
    }

    NullAndVoid() {
        /*
        // tag::null_not_a_primitive[]
        int i = null;    <-- Won't work
        // end::null_not_a_primitive[]
        */

        // tag::null_not_an_object[]
        // Null seems not to be a primitive type. So it must be some kind of object.
        Object ii = null;

        System.out.println(ii instanceof Object);       // false
        System.out.println(null instanceof Object);     // false

        // Sadly. Not an object.
        // end::null_not_an_object[]
    }

    /*
    // tag::void_type[]
    Void vv = void;
    // end::void_type[]
    */

    // tag::void_can_be_null[]
    Void v = null;
    // end::void_can_be_null[]

    Function<Supplier<String>, String> bar = Supplier::get;

    Supplier<Void> foo = () -> {
        throw new RuntimeException();
    };

    {
        Map<String, Void> m = new HashMap<>();
        m.put("Hallo", new Object());
        //bar.apply(foo);
    }



}
