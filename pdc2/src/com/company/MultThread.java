package com.company;

import java.util.Arrays;

public class MultThread extends Thread {
    private Result result;
    private RowOrColumn[] rows;
    private RowOrColumn[] columns;
    private int iteration;

    public MultThread(RowOrColumn[] rows, RowOrColumn[] columns, Result result, int iteration){
        this.rows = rows;
        this.columns = columns;
        this.result = result;
        this.iteration = iteration;
    }
    @Override
    public void run(){
        for(int i = 0;i<rows.length;i++){
                result.multiply(rows[i].indexInMatrix, rows[i].arrElements, columns[i].indexInMatrix, columns[i].arrElements, iteration);
        }
        result.iterations[iteration]++;
    }


}
