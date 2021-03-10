package com.company.task2;

import java.util.ArrayList;

public class Journal {
    private ArrayList<Group> groups;

    public Journal(ArrayList<Group> groups){
        this.groups = groups;
    }

    public void setMark(int weekNumber, int groupId, int studentId, int mark){
        var student = groups.stream().filter(x->x.getId()==groupId).findFirst().orElse(null).getStudentById(studentId);
            student.setMark(mark,weekNumber);
    }

    public synchronized void print(){
        for(int i=0;i<groups.size();i++){
            groups.get(i).print();
        }
        System.out.println();
    }
}
