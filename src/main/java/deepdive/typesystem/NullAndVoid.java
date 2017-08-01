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
    Void vv = void;     // <-- Won't work
    Void v = null;      // <-- `Void` can be `null`, but not `void`. 🤔
    // end::void_type[]
    */


    {
    // tag::void_as_generic[]
    Map<String, Void> m = new HashMap<>();
    m.put("Hallo", null);
    // end::void_as_generic[]
    }

}
