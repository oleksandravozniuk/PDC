package com.company;

public class Consumer implements Runnable {
    private int id;
    private Model model;
    private double timeMean;
    private double timeDeviation;
    private double tNext = Double.MAX_VALUE;

    public Consumer(Model model, double timeMean, double timeDeviation, int id) {
        this.model = model;
        this.timeMean = timeMean;
        this.timeDeviation = timeDeviation;
        this.id = id;
    }

    public void run() {
        while (model.getTCurrent() < model.getTimeModelling()) {
            while (model.queue.stream().count()>0){
                try {
                    Request request = model.queue.take();
                    long delay = (long)FunRand.unif(timeDeviation,timeMean);
                    tNext = model.getTCurrent() + delay;
                    //model.printInfo("Consumer in event request " + request.id);

                    Thread.sleep(delay);

                    moveTCurrent(tNext);

                    tNext = Double.MAX_VALUE;
                    //model.printInfo("Consumer out event request " + request.id);

                } catch (InterruptedException e) {} catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void moveTCurrent (double tNext){
        model.setTCurrent(tNext);
    }

    public int getId(){
        return id;
    }

    public double getTNext(){
        return tNext;
    }
}
