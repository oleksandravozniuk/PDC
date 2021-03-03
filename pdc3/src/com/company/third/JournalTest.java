package com.company.third;


import java.util.concurrent.ConcurrentHashMap;


public class JournalTest {
    public static void main(String[] args) {
        int weeksNumber = 10;
        int groupsCount = 3;
        ConcurrentHashMap[][] matrix = new ConcurrentHashMap[10][3];
        for(int i = 0;i<weeksNumber;i++){
            for(int j=0;j<groupsCount;j++){
                ConcurrentHashMap<Integer, Integer> group = new ConcurrentHashMap<Integer, Integer>();
                group.put(1,0);
                group.put(2,0);
                group.put(3,0);
                group.put(4,0);
                group.put(5,0);
                matrix[i][j] = group;
            }
        }

        Journal journal = new Journal(matrix);
    }
}
