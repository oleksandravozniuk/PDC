package com.company.task2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RecursiveAction;


public class TeacherTask extends RecursiveAction {
    private int id;
    private Journal journal;
    private int weeksNumber;
    private Random random = new Random();

    TeacherTask(Journal journal, int weeksNumber, int id) {
        super();
        this.journal  = journal;
        this.weeksNumber = weeksNumber;
        this.id = id;
    }

    @Override
    protected void compute() {
        List<TeacherTask> tasks = new LinkedList<>();

        for(int i=0;i<200;i++){
            TeacherTask task = new TeacherTask(journal,weeksNumber, i+1);
            tasks.add(task);
            task.fork();
        }

            rate();

    }

    private void rate(){
        for (int i =0;i<weeksNumber;i++){
            for(int j = 0;j<3;j++){
                for(int k = 0;k<5;k++){
                    int mark = 25;
                    journal.setMark(i,j,k,mark);
                }
            }
            //System.out.println("Teaacher " + id + " week " + i);
            //journal.print();
        }
    }
}
