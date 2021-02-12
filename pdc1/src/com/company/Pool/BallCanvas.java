package com.company.Pool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private Pocket pocket;
    private JLabel labelCount;

    public BallCanvas(JLabel labelCount){
        this.labelCount = labelCount;
    }

    public void addBall(Ball ball){
        this.balls.add(ball);
    }

    public void addPocket(Pocket pocket){
        this.pocket = pocket;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);
            b.draw(g2);
        }
        pocket.draw(g2);
        String count = Long.toString(balls.stream().filter(Ball::getInPocket).count());
        labelCount.setText(count);
    }
}
