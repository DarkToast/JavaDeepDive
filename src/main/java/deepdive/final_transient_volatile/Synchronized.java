package deepdive.final_transient_volatile;

import deepdive.Thread1;
import deepdive.Thread2;

import java.util.ArrayList;
import java.util.List;

// tag::unlocked[]
class Synchronized {
    // An exclusive monitor object.
    private final Object lock = new Object();

    // Only the reference is immutable!
    private final List<String> names = new ArrayList<>();

    @Thread1
    public int push(String name) {
        expensiveOperation();

        names.add(name);
        System.out.println("New list size: " + names.size());
        return names.size();
    }

    @Thread2
    public String pop() {
        if (!names.isEmpty()) {
            String element = names.remove(names.size() - 1);
            System.out.println("New list size: " + names.size());
            return element;
        } else {
            return "n/a";
        }
    }
// end::unlocked[]



// tag::this_synchronized[]
    @Thread1
    public synchronized int pushSynced(String name) {
        // Will lock all other threads
        expensiveOperation();

        names.add(name);
        System.out.println("New list size: " + names.size());
        return names.size();
    }

    @Thread2
    public synchronized String popSynced() {
        if (!names.isEmpty()) {
            String element = names.remove(names.size() - 1);
            System.out.println("New list size: " + names.size());
            return element;
        } else {
            return "n/a";
        }
    }
// end::this_synchronized[]




// tag::lock_synchronized[]
    @Thread1
    public int pushSyncedLocal(String name) {

        // not thread safe!
        expensiveOperation();

        // Only the mutable shared object is synchronized.
        synchronized (lock) {
            names.add(name);
            System.out.println("New list size: " + names.size());
            return names.size();
        }
    }

    @Thread2
    public String popSyncedLocal() {
        synchronized (lock) {
            if (!names.isEmpty()) {
                String element = names.remove(names.size() - 1);
                System.out.println("New list size: " + names.size());
                return element;
            } else {
                // Hmmm. Too many "n/a" values on the other thread.
                return "n/a";
            }
        }
    }
// end::lock_synchronized[]




// tag::lock_synchronized_notified[]
    @Thread1
    public int pushNotified(String name) {

        expensiveOperation();

        synchronized (lock) {
            names.add(name);
            System.out.println("New list size: " + names.size());

            // Notify all waiting threads.
            lock.notifyAll();

            return names.size();
        }
    }

    @Thread2
    public String popNotified() {
        synchronized (lock) {

            // Locking wait - must be notified somewhere to prevent a dead lock!
            if(names.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    this.notifyAll();
                }
            }

            // The synchronized mutable access:
            String element = names.remove(names.size() - 1);
            System.out.println("New list size: " + names.size());
            return element;
        }
    }
// end::lock_synchronized_notified[]



    private void expensiveOperation() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            this.notifyAll();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Synchronized s = new Synchronized();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                s.pushNotified("Hallo" + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(s.popNotified());
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
