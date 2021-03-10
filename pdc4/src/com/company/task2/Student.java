package com.company.task2;

public class Student {
    private int id;
    private int[] marks;
    public Student(int id, int weeksNumber){
        this.id = id;
        marks = new int[weeksNumber];
        for(int i=0;i<weeksNumber;i++){
            marks[i] = 0;
        }
    }

    public synchronized void setMark(int mark, int weekNumber){
        marks[weekNumber] += mark;
    }

    public void print(){
        System.out.print("Student " + id + ": ");
        for (int i=0;i<marks.length;i++){
            System.out.print(marks[i] + "  ");
        }
    }

    public int getId(){
        return id;
    }
}
