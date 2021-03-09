package com.company.third;


import java.util.Random;

class TeacherThread extends Thread {
    private int id;
private Journal journal;
private int weeksNumber;
private Random random = new Random();

    public TeacherThread(Journal journal, int weeksNumber, int id){
        this.journal  = journal;
        this.weeksNumber = weeksNumber;
        this.id = id;
    }
    public void run(){
        for (int i =0;i<weeksNumber;i++){
            for(int j = 0;j<3;j++){
                for(int k = 0;k<5;k++){
                    int mark = 25;
                    journal.setMark(i,j,k,mark);
                }
            }
            System.out.println("Teaacher " + id + " week " + i);
            journal.print();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}