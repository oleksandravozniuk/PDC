package com.company;

public class StatThread extends Thread{
    Model model;
    public StatThread(Model model){
        this.model = model;
    }
    @Override
    public void run() {
        while (model.getTCurrent()<model.getTimeModelling()){
            model.printInfo("");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
