package deepdive.final_transient_volatile;

import deepdive.Thread1;
import deepdive.Thread2;

// tag::class[]
class Volatile {
    long number;
    volatile boolean flag;

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
// end::class[]
