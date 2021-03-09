package com.company.second.Modified;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<String> drop;

    public Producer(BlockingQueue<String> drop) {
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
            try {
                drop.put(importantInfo[i].toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(random.nextInt(100));
//            } catch (InterruptedException e) {}
        }
        try {
            drop.put("DONE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}