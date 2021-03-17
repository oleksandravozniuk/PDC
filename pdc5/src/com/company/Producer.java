package com.company;


public class Producer implements Runnable {
    Model model;
    double timeMean;
    double timeDeviation;
    double tNext = Double.MAX_VALUE;
    static int requestId;

    public Producer(Model model, double timeMean, double timeDeviation) {
        this.model = model;
        this.timeMean = timeMean;
        this.timeDeviation = timeDeviation;
    }

    public void run() {
        long nextDelay = 0;
        while (model.getTCurrent() < model.getTimeModelling()) {
            try {
                long delay;
                if(nextDelay==0){
                    delay = (long) FunRand.norm(timeMean,timeDeviation);
                } else{
                    delay = nextDelay;
                }
                Thread.sleep(delay);
                Request request = new Request(requestId++);

                if(model.queue.remainingCapacity()==0){
                    model.failCount++;
                }

                model.queue.put(request);
                model.addsToQueueCount++;

                double tCur = model.getTCurrent() + delay;
                moveTCurrent(tCur);

                nextDelay = (long) FunRand.norm(timeMean,timeDeviation);
                tNext = model.getTCurrent() + nextDelay;

                //model.printInfo("Producer event");

            } catch (InterruptedException e) {} catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void moveTCurrent (double tNext){
        model.setTCurrent(tNext);
    }

    public double getTNext(){
        return tNext;
    }
}
