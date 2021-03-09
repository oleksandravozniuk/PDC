package com.company.third;


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class JournalTest {
    public static void main(String[] args) {
        int weeksNumber = 7;
        Journal journal = new Journal(createGroups(weeksNumber));

        TeacherThread[] threads = new TeacherThread[200];
        for(int i = 0;i<200;i++){
            TeacherThread tt = new TeacherThread(journal,weeksNumber,i);
            threads[i]=tt;
        }
        for(int i = 0;i<200;i++){
            threads[i].start();
        }
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
