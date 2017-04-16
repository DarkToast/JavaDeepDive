package deepdive.final_transient_volatile;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ConcurrentLib {

    // tag::atomicLong[]
    public long atomicLong() {
        AtomicLong aLong = new AtomicLong(42L);
        return aLong.incrementAndGet();
    }
    // end::atomicLong[]


    // tag::copyOnWriteArrayList[]
    public void copyOnWriteArrayList() {
        List<String> list = new CopyOnWriteArrayList<>();

        // Copies the complete list on every `add`
        list.add("Hallo");
        list.add("Welt");

        // Always has an immutable state of the list.
        list.get(0);
    }
    // end::copyOnWriteArrayList[]


    // tag::reentrantLock[]
    public void reentrantLock() {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
        // Make some synchronized stuff
        reentrantLock.unlock();
    }
    // end::reentrantLock[]


    // tag::readWriteLock[]
    public void readWriteLock() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        // Blocks only if there is a locked WriteLock
        readLock.lock();
        readLock.unlock();

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        // Blocks, if there is a locked WriteLock or ReadLock.
        writeLock.lock();
        writeLock.unlock();
    }
    // end::readWriteLock[]


    // tag::semaphore[]
    public void semaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);

        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire(); // blocks!

        semaphore.release();
    }
    // end::semaphore[]
}
