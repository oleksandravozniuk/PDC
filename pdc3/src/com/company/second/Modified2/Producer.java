package com.company.second.Modified2;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private Queue<String> drop;

    public Producer(Queue<String> drop) {
        this.drop = drop;
    }

    public void run() {
        Integer importantInfo[] = new Integer[100];
        for (int i = 0; i < importantInfo.length; i++)
        {
            importantInfo[i] = i;
        }

        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.add(importantInfo[i].toString());
                        try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {}
        }
        drop.add("DONE");

    }
}