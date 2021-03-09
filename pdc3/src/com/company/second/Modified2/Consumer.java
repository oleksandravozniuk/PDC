package com.company.second.Modified2;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private Queue<String> drop;

    public Consumer(Queue<String> drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.peek(); ! message.equals("DONE"); message = drop.peek()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
    }
}