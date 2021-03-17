package com.company;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
    public BlockingQueue<Request> queue;
    private double tCurrent = 0.0;
    private double timeModelling;
    private ArrayList<Consumer> consumers;
    private Producer producer;

    private double queueCount;
    public int failCount;
    public int addsToQueueCount;

    public ExperimentStatistics expStat;

    public Model(double timeModelling, int queueCapacity, int channelsCount, ExperimentStatistics expStat){
        this.producer = new Producer(this,3,1);
        ArrayList<Consumer> consumers = new ArrayList<>();
        for(int i = 0; i<channelsCount;i++){
            Consumer consumer = new Consumer(this,30,5,i);
            consumers.add(consumer);
        }
        this.consumers = consumers;
        this.timeModelling = timeModelling;
        this.queue = new ArrayBlockingQueue<>(queueCapacity);
        this.expStat = expStat;
    }

    public void simulate() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(consumers.size()+1);
        pool.execute(producer);
        for (var consumer: consumers){
            pool.execute(consumer);
        }
        pool.shutdown();

        while(pool.isShutdown()){
            Thread.sleep(5000);
           printStatistics();
           updateStat();
            break;
        }

    }

    public synchronized void setTCurrent(double tNext){
        updateStatistics(getQueueN()*(tNext-tCurrent));
        tCurrent = tNext;
    }

    public double getTCurrent() {
        return tCurrent;
    }

    public double getTimeModelling(){
        return timeModelling;
    }

    public int getQueueN(){
        return queue.size();
    }

    public synchronized void printInfo(String additionInfo){
        System.out.println(additionInfo);
        System.out.println("TCurr: " + tCurrent);
        System.out.println("Queue: " + getQueueN());
        System.out.println("Producer" + "  tNext: " + producer.getTNext());
        for(var consumer: consumers){
            System.out.println("consumer" + consumer.getId() + "  tNext: " + consumer.getTNext());
        }
        System.out.println();
    }

    public void updateStatistics(double queueCount){
        this.queueCount+=queueCount;
    }

    public double getAverageQueue(){
        return queueCount/timeModelling;
    }

    public void printStatistics(){
        System.out.print("average queue count: " + getAverageQueue() + "  ");
        System.out.print("Failure probability: " + (double)failCount/addsToQueueCount);
        System.out.println();
    }

    public synchronized void updateStat(){
        expStat.sumAverageQueue+=getAverageQueue();
        expStat.sumFailureProbability+=(double)failCount/addsToQueueCount;
    }
}
