package deepdive.final_transient_volatile;

import deepdive.Thread2;
import deepdive.Thread1;

import java.util.Objects;

// tag::class[]
class ConcurentUsage {
    // static
    Point f;

    @Thread1
    void writer() {
        f = new Point(1, 2);
    }

    @Thread2
    void reader() {
        if (f != null) {
            int i = f.x;    // <- The JMM guarantees that this is 3!
            Object y = f.y;    // <- Maybe sees the value 0 -
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this(point.x, point.y);
    }

    public synchronized void setXY(int x, int y){
        this.x = x;
        //Simulate some resource intensive work that starts EXACTLY at this point, causing a small delay
        try {
            Thread.sleep(10 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    public String toString(){
        return "X: " + x + " Y: " + y;
    }

    public static void main(String[] args) throws InterruptedException {
        final Point originalPoint = new Point(1,1);

        Thread t0 = new Thread(() -> {
            originalPoint.setXY(2, 2);
            System.out.println("Original : " + originalPoint.toString());
        });

        Thread t1 = new Thread(() -> {
            Point copySafePoint = new Point(originalPoint);
            System.out.println("Copy : " + copySafePoint.toString());
        });

        t0.start();
        t1.start();

        t0.join();
        t1.join();
    }
}


// end::class[]