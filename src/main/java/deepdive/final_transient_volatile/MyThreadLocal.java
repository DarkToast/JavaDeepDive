package deepdive.final_transient_volatile;

import java.util.Random;

public class MyThreadLocal {
    ThreadLocal<Integer> integer = new ThreadLocal<>();

    public void set() {
        integer.set(new Random().nextInt());
    }

    public int get() {
        return integer.get();
    }
}
