package com.company.third;

import java.util.concurrent.ConcurrentMap;

public class Journal {
    private ConcurrentMap<Integer, Integer>[][] groupPages;

    public Journal(ConcurrentMap<Integer, Integer>[][] groupPages){
        this.groupPages = groupPages;
    }

    public void setMark(int weekNumber, int groupNumber, int studentId, int mark, int teacherId){
        groupPages[weekNumber][groupNumber].put(studentId,mark);
        print(studentId, teacherId, groupNumber, weekNumber);
    }

    public void print(int studentId, int teacherId, int groupId, int weekNumber){
        System.out.println("Week " + weekNumber + " Student " + studentId + " mark: " + groupPages[weekNumber][groupId].get(studentId) + " by teacher " + teacherId);
    }
}
