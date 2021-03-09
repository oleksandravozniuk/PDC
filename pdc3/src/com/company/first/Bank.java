package com.company.first;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
    public static final int NTEST = 10000;
    private AtomicIntegerArray accounts;
    private AtomicLong ntransacts = new AtomicLong(0);

    public Bank(int n, int initialBalance){
        accounts = new AtomicIntegerArray(n);
        int i;
        for (i = 0; i < accounts.length(); i++)
            accounts.addAndGet(i,initialBalance);
        ntransacts.set(0);
    }
//    public void transfer(int from, int to, int amount)
//            throws InterruptedException{
//        accounts[from] -= amount;
//        accounts[to] += amount;
//        ntransacts++;
//        if (ntransacts % NTEST == 0)
//            test();
//    }

    public void transfer(int from, int to, int amount) throws InterruptedException{

            accounts.addAndGet(from,-amount);
            accounts.addAndGet(to,amount);

            ntransacts.incrementAndGet();

            if (ntransacts.get() % NTEST == 0)
                test();
    }

    public void test(){
        int sum = 0;
        for (int i = 0; i < accounts.length(); i++)
        sum += accounts.get(i) ;
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);
    }
    public int size(){
        return accounts.length();
    }
}