package com.company;

public class MultThread extends Thread {
    int[] line;
    int[] column;
    int sum = 0;

    public MultThread(int[] line, int[] column){
        this.line = line;
        this.column = column;
    }
    @Override
    public void run(){
        for(int i = 0; i < line.length; i++){
            sum+=line[i]*column[i];
        }
    }

    public int getValue() {
        return sum;
    }

}
