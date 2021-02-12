package com.company.Pool;


public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
            try{
                for(int i = 1; i < 10000; i++){
                    if(b.getX() == Pocket.x && b.getY() == Pocket.y){
                        b.setInPocket();
                        return;
                    }
                    b.move();
                    System.out.println("Thread name = " + Thread.currentThread().getName());
                    Thread.sleep(5);
                }
            } catch(InterruptedException ex){
            }
    }
}
