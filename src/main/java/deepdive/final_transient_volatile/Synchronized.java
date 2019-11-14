package deepdive.final_transient_volatile;

import deepdive.Sleep;
import deepdive.Thread1;
import deepdive.Thread2;

import java.util.ArrayList;
import java.util.List;

// tag::unlocked[]
class Synchronized {
    // An exclusive monitor object.


    // Only the reference is immutable!


    @Thread1
    public int push(String name) {
        expensiveOperation();

        namesSharedState.add(name);
        System.out.println("New list size: " + namesSharedState.size());
        return namesSharedState.size();
    }

    @Thread2
    public String pop() {
        if (!namesSharedState.isEmpty()) {
            String element = namesSharedState.remove(namesSharedState.size() - 1);
            System.out.println("New list size: " + namesSharedState.size());
            return element;
        } else {
            return "n/a";
        }
    }
// end::unlocked[]


    // tag::this_synchronized[]
    private final List<String> namesSharedState = new ArrayList<>();

    @Thread1
    public synchronized void pushSynced(String name) {
        expensiveOperation();
        namesSharedState.add(name);
    }

    @Thread2
    public synchronized String popSynced() {
        if (!namesSharedState.isEmpty()) {
            return namesSharedState.remove(namesSharedState.size() - 1);
        } else {
            return "n/a";
        }
    }
// end::this_synchronized[]


    // tag::lock_synchronized[]
    private final Object lock = new Object();

    @Thread1
    public void pushSyncedLocal(String name) {
        expensiveOperation();

        synchronized (lock) {
            namesSharedState.add(name);
        }
    }

    @Thread2
    public String popSyncedLocal() {
        synchronized (lock) {
            if (!namesSharedState.isEmpty()) {
                return namesSharedState.remove(namesSharedState.size() - 1);
            } else {
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
            namesSharedState.add(name);
            System.out.println("New list size: " + namesSharedState.size());

            // Notify all waiting threads.
            lock.notifyAll();

            return namesSharedState.size();
        }
    }

    @Thread2
    public String popNotified() {
        synchronized (lock) {

            // Locking wait - must be notified somewhere to prevent a dead lock!
            if (namesSharedState.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    this.notifyAll();
                }
            }

            // The synchronized mutable access:
            String element = namesSharedState.remove(namesSharedState.size() - 1);
            System.out.println("New list size: " + namesSharedState.size());
            return element;
        }
    }
// end::lock_synchronized_notified[]


    private void expensiveOperation() {
        Sleep.sleep();
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
