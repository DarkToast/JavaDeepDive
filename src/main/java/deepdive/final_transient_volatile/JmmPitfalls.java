package deepdive.final_transient_volatile;

import deepdive.Thread1;
import deepdive.Thread2;

// tag::jmm[]
class JmmPitfalls {
    long number;
    Object o;
    boolean flag;

    @Thread1
    void write() {
        number = 999999999999L;
        flag = true;
        o = new Object();
    }

    @Thread2
    void read() {
        if(flag) {
            System.out.println(o);
            o = null;
            number = 0;
            flag = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JmmPitfalls pitfalls = new JmmPitfalls();
        Thread thread1 = new Thread(() -> {
            while(true) {
                pitfalls.write();
            }
        });
        Thread thread2 = new Thread(() -> {
            while(true) {
                pitfalls.read();
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(15000);
        thread1.interrupt();
        thread2.interrupt();
    }
}
// end::jmm[]
