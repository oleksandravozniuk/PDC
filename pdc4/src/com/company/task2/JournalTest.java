package com.company.task2;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;


public class JournalTest {
    public static void main(String[] args) {
        int weeksNumber = 7;
        Journal journal = new Journal(createGroups(weeksNumber));


//        ForkJoinPool pool = new ForkJoinPool(200);
//        pool.invoke(new TeacherTask(journal, weeksNumber,0));

        int n = 500;
        double timeMultiThreadSum = 0;
        double timeForkJoinSum = 0;
        for(int i = 0;i<n;i++){
            double startTime = 0;
            double finishTime = 0;

            TeacherThread[] threads = new TeacherThread[200];
            for(int k = 0;k<200;k++){
                TeacherThread tt = new TeacherThread(journal,weeksNumber,k);
                threads[k]=tt;
            }
            startTime = System.currentTimeMillis();
            for(int j = 0;j<200;j++){
                threads[j].start();
            }
            finishTime = System.currentTimeMillis();
            timeMultiThreadSum+=(finishTime-startTime);

            startTime = System.currentTimeMillis();
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(new TeacherTask(journal, weeksNumber,0));
            finishTime = System.currentTimeMillis();
            timeForkJoinSum+=(finishTime-startTime);
        }
        double timeMultiThread = timeMultiThreadSum/n;
        double timeForkJoin = timeForkJoinSum/n;
        System.out.println("Time multi thread: " + timeMultiThread);
        System.out.println("Time fork join: " + timeForkJoin);
        System.out.println("Speed up: " +  timeMultiThread/timeForkJoin);
    }

    public static ArrayList<Group> createGroups(int weeksNumber){
        ArrayList<Group> groups = new ArrayList<>();

        //group1
        Student s0 = new Student(0, weeksNumber);
        Student s1 = new Student(1, weeksNumber);
        Student s2 = new Student(2, weeksNumber);
        Student s3 = new Student(3, weeksNumber);
        Student s4 = new Student(4, weeksNumber);
        ArrayList<Student> students0 = new ArrayList<>();
        students0.add(s0); students0.add(s1); students0.add(s2); students0.add(s3); students0.add(s4);
        Group group0 = new Group(0, students0);

        //group2
        Student s5 = new Student(0, weeksNumber);
        Student s6 = new Student(1, weeksNumber);
        Student s7 = new Student(2, weeksNumber);
        Student s8 = new Student(3, weeksNumber);
        Student s9 = new Student(4, weeksNumber);
        ArrayList<Student> students1 = new ArrayList<>();
        students1.add(s5); students1.add(s6); students1.add(s7); students1.add(s8); students1.add(s9);
        Group group1 = new Group(1, students1);

        //group3
        Student s10 = new Student(0, weeksNumber);
        Student s11 = new Student(1, weeksNumber);
        Student s12 = new Student(2, weeksNumber);
        Student s13 = new Student(3, weeksNumber);
        Student s14 = new Student(4, weeksNumber);
        ArrayList<Student> students2 = new ArrayList<>();
        students2.add(s10); students2.add(s11); students2.add(s12); students2.add(s13); students2.add(s14);
        Group group2 = new Group(2, students2);

        groups.add(group0);
        groups.add(group1);
        groups.add(group2);

        return groups;
    }
}
