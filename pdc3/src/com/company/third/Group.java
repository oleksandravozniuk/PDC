package com.company.third;

import java.util.ArrayList;

public class Group {
    private int id;
    private ArrayList<Student> students;

    public Group(int id, ArrayList<Student> students){
        this.id = id;
        this.students = students;
    }

    public void print(){
        System.out.print("Group " + id + ":");
        for (int i=0;i<students.size();i++){
            students.get(i).print();
        }
        System.out.println();
    }

    public int getId(){
        return id;
    }

    public Student getStudentById(int studentId){
        return students.stream().filter(x->x.getId()==studentId).findFirst().orElse(null);
    }
}
