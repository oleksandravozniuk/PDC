package com.company;

public class Main {

    public static void main(String[] args) {
        //input values
        int size = 10;
        int[][] a = createMatrix(size);
        int[][] b = createMatrix2(size);
        //--------------------------------
        printMatrix(b, 10);
    }

    private static int[][] createMatrix(int size){
        int[][] matrix = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                matrix[i][j] = j + 1;
            }
        }
        return matrix;
    }

    private static int[][] createMatrix2(int size){
        int[][] matrix = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                matrix[i][j] = 1;
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix, int size){
        for(int i = 0; i<size; i++){
            for(int j=0;j<size;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private static void multiplyMatrixes(int[][] matrixA, int[][] matrixB, int size){
        for(int i=0;i<size;i++){

        }
    }
}
