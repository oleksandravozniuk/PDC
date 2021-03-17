package com.company;

public class Simulator implements Runnable{
private Model model;
public Simulator(Model model){
    this.model = model;
}
    @Override
    public void run() {
        try {
            model.simulate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
