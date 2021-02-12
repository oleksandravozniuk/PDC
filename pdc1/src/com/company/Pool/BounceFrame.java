package com.company.Pool;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BounceFrame extends JFrame {
    private static BallCanvas ballCanvas;

    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        var label = new JLabel("0");
        this.ballCanvas = new BallCanvas(label);

        Pocket pocket = new Pocket(ballCanvas);
        ballCanvas.addPocket(pocket);

        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.ballCanvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStartRed = new JButton("Start Red");
        JButton buttonStartBlue = new JButton("Start Blue");
        JButton buttonExperiment = new JButton("Experiment");
        JButton buttonJoin = new JButton("Join");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> {
            Ball b = new Ball(ballCanvas, Color.darkGray, getRandomCoordinate(), getRandomCoordinate());
            ballCanvas.addBall(b);

            BallThread thread = new BallThread(b);
            thread.start();
            System.out.println("Thread created = " +
                    thread.getName());
        });
        buttonStartRed.addActionListener(e -> {
            Ball b = new Ball(ballCanvas, Color.red, getRandomCoordinate(), getRandomCoordinate());
            ballCanvas.addBall(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
            System.out.println("Red Thread created = " +
                    thread.getName());
        });
        buttonStartBlue.addActionListener(e -> {
            Ball b = new Ball(ballCanvas, Color.blue, 50, 50);
            ballCanvas.addBall(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
            System.out.println("Blue Thread created = " +
                    thread.getName());
        });
        buttonExperiment.addActionListener(e -> {
            Ball b = new Ball(ballCanvas, Color.red, 50, 50);
            ballCanvas.addBall(b);
            BallThread thread = new BallThread(b);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
            System.out.println("Red Thread created = " +
                    thread.getName());
            for(int i = 0; i<50; i++){
                Ball b2 = new Ball(ballCanvas, Color.blue, 50, 50);
                ballCanvas.addBall(b2);
                BallThread thread2 = new BallThread(b2);
                thread2.setPriority(Thread.MIN_PRIORITY);
                thread2.start();
                System.out.println("Blue Thread created = " +
                        thread2.getName());
            }
        });
        buttonJoin.addActionListener(e -> {
            Ball g = new Ball(ballCanvas, Color.darkGray, getRandomCoordinate(), getRandomCoordinate());
            ballCanvas.addBall(g);
            BallThread threadG = new BallThread(g);

            Ball b = new Ball(ballCanvas, Color.blue, getRandomCoordinate(), getRandomCoordinate());
            ballCanvas.addBall(b);
            BallThread threadB = new BallThread(b);

            Ball r = new Ball(ballCanvas, Color.red, getRandomCoordinate(), getRandomCoordinate());
            ballCanvas.addBall(r);
            BallThread threadR = new BallThread(r);

            threadG.start();

            try {
                threadG.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            threadB.start();
            threadR.start();
        });
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(buttonStartRed);
        buttonPanel.add(buttonStartBlue);
        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonJoin);
        buttonPanel.add(label);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private static int getRandomCoordinate(){
        int coordinate = new Random().nextInt(ballCanvas.getWidth());
        return coordinate;
    }

    public static void join(){
        Ball g = new Ball(ballCanvas, Color.darkGray, getRandomCoordinate(), getRandomCoordinate());
        ballCanvas.addBall(g);
        BallThread threadG = new BallThread(g);

        Ball b = new Ball(ballCanvas, Color.blue, getRandomCoordinate(), getRandomCoordinate());
        ballCanvas.addBall(b);
        BallThread threadB = new BallThread(b);

        Ball r = new Ball(ballCanvas, Color.red, getRandomCoordinate(), getRandomCoordinate());
        ballCanvas.addBall(r);
        BallThread threadR = new BallThread(r);

        threadG.start();
        threadB.start();

        try {
            threadG.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        threadR.start();
    }
}
