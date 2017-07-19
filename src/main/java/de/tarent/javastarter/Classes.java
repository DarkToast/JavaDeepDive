package de.tarent.javastarter;

import java.util.ArrayList;

public class Classes {

    // tag::classes[]
    Object o = new Object();

    public Classes() {
        o = "Hallo Welt";     // String is not a primitive!
        o = new ArrayList();  // Plain object
        o = true;             // Autoboxing of boolean
        o = 1;                // Autoboxing of int

        System.out.println(o.toString());
        System.out.println(o.equals(new Object()));


        "Hallo Welt".toString();
        System.out.println("Hallo" instanceof Object);
    }
    // end::classes[]
}