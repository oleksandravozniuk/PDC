package com.company;

import com.company.Counter.Counter;
import com.company.Counter.DecrementThread;
import com.company.Counter.IncrementThread;
import com.company.Pool.BounceFrame;
import com.company.Symbols.SymbolThread;
import com.company.Symbols.SymbolThreadDash;
import com.company.Symbols.SymbolThreadVertical;
import com.company.Symbols.SymbolWriter;

import javax.swing.*;

public class Bounce {
    public static void main(String[] args) throws InterruptedException {
//        BounceFrame frame = new BounceFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.setVisible(true);
//        System.out.println("Thread name = " + Thread.currentThread().getName());
//
//        BounceFrame.join();

////task5.1
//        for(int i = 0; i<100;i++)
//        {
//            try{
//                task1();
//                System.out.print("\n");
//            }catch (InterruptedException ex){ }
//        }
//
//
////task 5.2
//        task2();

        //task 6
        task3();


    }

    private static void task1() throws InterruptedException {
        SymbolThread st1 = new SymbolThread("-");
        SymbolThread st2 = new SymbolThread("|");
        st1.start();
        st2.start();
        st1.join();
        st2.join();
    }

    private static void task2(){
        SymbolWriter symbolWriter = new SymbolWriter();

        SymbolThreadDash st1 = new SymbolThreadDash(symbolWriter);
        SymbolThreadVertical st2 = new SymbolThreadVertical(symbolWriter);
        st1.start();
        st2.start();
    }

    private static void task3(){
        Counter counter = new Counter(0,1);
        IncrementThread it = new IncrementThread(counter);
        DecrementThread dt = new DecrementThread(counter);
        it.start();
        dt.start();



    }
}
