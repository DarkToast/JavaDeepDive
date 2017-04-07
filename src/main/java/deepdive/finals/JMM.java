package deepdive.finals;

import deepdive.Thread1;
import deepdive.Thread2;

// tag::jmm[]
class JMM {
    String message;

    @Thread1
    void write() {
        message = "Hallo welt";
    }

    @Thread2
    void read() {
        System.out.println(message);
    }
}
// end::jmm[]
