package com.company;

import java.util.Arrays;

public class Result {
    public int[][] resultMatrix;
    public int[] iterations;
    private int size;
    private boolean printed;

    public Result(int size){
        resultMatrix = new int[size][size];
        iterations = new int[size];
        this.size = size;
    }

    public synchronized void multiply(int lineIndex, int[] line, int columnIndex, int[] column, int iteration) {
        while(checkIteration(iteration)){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int sum = 0;
        for (int i=0;i<line.length;i++){
            sum+=line[i]*column[i];
        }
        resultMatrix[lineIndex][columnIndex] = sum;
        notifyAll();
//        if (Arrays.stream(iterations).sum() == size*size-1 && !printed){
//            printMatrix();
//            printed = true;
//        }
    }

    private synchronized boolean checkIteration(int iteration){
        for(int i=0;i<size;i++){
            while (i<iteration && iterations[i]<size){
                return true;
            }
        }
        return false;
    }

    private synchronized void printMatrix(){
        for(int i = 0; i<size; i++){
            for(int j=0;j<size;j++){
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
