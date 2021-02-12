package com.company.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private int method;
    private ReentrantLock lock = new ReentrantLock();

    public Counter(int counter, int method){
        this.counter = counter;
        this.method = method;
    }

    public void increment(){this.counter++;}
    public void decrement(){this.counter--;}

    public synchronized void incrementSync(){this.counter++;}
    public synchronized void decrementSync(){this.counter--;}

    public void incrementSyncBlock(){
        synchronized (this){
            this.counter++;
        }
    }
    public void decrementSyncBlock(){
        synchronized (this){
            this.counter--;
        }
    }

    public void incrementLock(){
        lock.lock();
        try {
            this.counter++;
        } finally {
            lock.unlock();
        }
    }
    public void decrementLock(){
        lock.lock();
        try {
            this.counter--;
        } finally {
            lock.unlock();
        }
    }


    public int getCounter(){
        return this.counter;
    }
    public int getMethod(){
        return this.method;
    }
}
