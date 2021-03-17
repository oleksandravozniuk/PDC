package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {
        int numberOfExperiments = 1;
        ExperimentStatistics expStat = new ExperimentStatistics();

        ExecutorService pool = Executors.newFixedThreadPool(numberOfExperiments);
        for (int i=0;i<numberOfExperiments;i++){
            Model model = new Model(5000,5,6, expStat);
            StatThread experimentStatistics = new StatThread(model);
            pool.execute(new Simulator(model));
            experimentStatistics.start();
        }
        pool.shutdown();
        Thread.sleep(6000);
        System.out.println("Experiment result, number of runs = " + numberOfExperiments);
        System.out.println("Average queue: " + expStat.sumAverageQueue/numberOfExperiments);
        System.out.println("Average failure probability: " + expStat.sumFailureProbability/numberOfExperiments);
    }
}
