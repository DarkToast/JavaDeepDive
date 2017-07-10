package deepdive.typesystem;

import java.util.ArrayList;

public class Classes {

    Object o = new Object();

    public Classes() {
        o = "Hallo Welt";   // <-- String is not a primitive!
        o = new ArrayList();
        o = true;
        o = 1;
    }

    public void boxing(int i) {
        Integer ii = i;

        // But this doesn't work. So autoboxing works not everywhere for every literal.
        //System.out.println(2 instanceof Integer);
    }

    public void unboxing(Integer i) {
        int ii = i;
    }
}