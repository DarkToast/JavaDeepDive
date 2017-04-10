package deepdive.final_transient_volatile;

import deepdive.Thread1;
import deepdive.Thread2;

// tag::jmm[]
class JmmPitfalls {
    long number;
    boolean flag;

    @Thread1
    void write() {
        number = 999999999999L;
        flag = true;
    }

    @Thread2
    void read() {
        if(flag) {
            System.out.println(number);
        }
    }
}
// end::jmm[]
