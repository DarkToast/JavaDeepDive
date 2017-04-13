package deepdive.final_transient_volatile;

import deepdive.Thread2;
import deepdive.Thread1;

// tag::class[]
class FinalConcurrent {
    final int x;  // <- X is final
    int y;        // <- Y not

    // not synchronized!
    public FinalConcurrent() {
        x = 3;
        y = 4;
    }


    // static
    static FinalConcurrent f;

    @Thread1
    static void writer() {
        f = new FinalConcurrent();
    }

    @Thread2
    static void reader() {
        if(f != null) {
            int i = f.x;    // <- The JMM guarantees that this is 3!
            int y = f.y;    // <- Maybe sees the value 0 - Sequential Consistency!
        }
    }
}
// end::class[]