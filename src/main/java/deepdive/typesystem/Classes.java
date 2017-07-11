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
}