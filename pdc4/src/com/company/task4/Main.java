package com.company.task4;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File("C:\\Users\\Oleksandra\\Desktop\\PDC\\PDC\\pdc4\\documents"));


//        Statistics statistics = wordCounter.countStatInParallel(folder);
//        System.out.println("min value: " + statistics.minValue);
//        System.out.println("max value: " + statistics.maxValue);
//        System.out.println("words count: " + statistics.count);
//        for(int key: statistics.countLength.keySet()){
//            System.out.println(key + "      " + statistics.countLength.get(key) + "     " + (double)statistics.countLength.get(key)/statistics.count);
//        }

        int n = 100;
        double timeOneThreadSum = 0;
        double timeParallelSum = 0;
        for(int i = 0;i<n;i++){
            double startTime = 0;
            double finishTime = 0;

            startTime = System.currentTimeMillis();
            wordCounter.countStatOnSingleThread(folder);
            finishTime = System.currentTimeMillis();
            timeOneThreadSum+=(finishTime-startTime);

            startTime = System.currentTimeMillis();
            wordCounter.countStatInParallel(folder);
            finishTime = System.currentTimeMillis();
            timeParallelSum+=(finishTime-startTime);
        }
        double timeOneThread = timeOneThreadSum/n;
        double timeParallel = timeParallelSum/n;
        System.out.println("Time one thread: " + timeOneThread);
        System.out.println("Time parallel: " + timeParallel);
        System.out.println("Speed up: " +  timeOneThread/timeParallel);
    }
}
