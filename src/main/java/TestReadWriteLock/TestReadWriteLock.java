package TestReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by DBQ on 2016/12/20.
 */


public class TestReadWriteLock {

    public static void main(String[] args) {

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        readWriteLock.readLock().lock();

// multiple readers can enter this section

// if not locked for writing, and no writers are waiting to lock for writing.

        readWriteLock.readLock().unlock();

        readWriteLock.writeLock().lock();

// only one writer can enter this section,

// and only if no threads are currently reading.

        readWriteLock.writeLock().unlock();
    }
}
