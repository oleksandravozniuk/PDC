package com.company;

import java.util.Random;

public class FunRand
{
    public static double exp(double timeMean) {
        double a = 0;
        while (a == 0) {
            a = Math.random();
        }
        a = -timeMean * Math.log(a);

        return a;
    }

    public static double unif(double timeMin, double timeMax) throws Exception {
        double a = 0;
        double b = 0;
        while (b<=0){
            while (a == 0) {
                a = Math.random();
            }
            b = timeMin + a * (timeMax - timeMin);
        }
        return b;
    }

    public static double norm(double timeMean, double timeDeviation) throws Exception {
        double a = 0;
        while (a<=0){
            Random r = new Random();
            a = timeMean + timeDeviation * r.nextGaussian();
        }
        return a;
    }
}