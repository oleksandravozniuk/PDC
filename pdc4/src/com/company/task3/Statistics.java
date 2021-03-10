package com.company.task3;

import java.util.HashMap;

public class Statistics {
    public int minValue;
    public int maxValue;
    public int count;
    public HashMap<Integer,Integer> countLength;

    public Statistics(){
        minValue = Integer.MAX_VALUE;
        maxValue = 0;
        count = 0;
        countLength = new HashMap<>();
    }

    public void mergeStat(Statistics newStat){
        if(maxValue < newStat.maxValue){
            maxValue = newStat.maxValue;
        }
        if(minValue > newStat.minValue){
            minValue = newStat.minValue;
        }
        for(int key: newStat.countLength.keySet()){
            if(countLength.containsKey(key)){
                countLength.replace(key,countLength.get(key) + newStat.countLength.get(key));
            } else{
                countLength.put(key,newStat.countLength.get(key));
            }
        }
        count+=newStat.count;
    }
}
