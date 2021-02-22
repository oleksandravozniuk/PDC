package Striped;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        int value = 0;
        //input values

            int size = 1000;
            int threadsCount = 50;
            int[][] a = createMatrix(size);
            int[][] b = createMatrix2(size);
            //--------------------------------
//        printMatrix(a, size);

            Result result = new Result(size);
            long m = System.currentTimeMillis();
            multiplyMatrixes(a,b,size,threadsCount,result);
            //multiplyMatrixesSerial(a,b,result);
            //printMatrix(result.resultMatrix,size);

        System.out.println((double) (System.currentTimeMillis() - m)/1000);
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
//                if(i==j){
                    matrix[i][j] = 1;
//                }
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

    private static void multiplyMatrixes(int[][] matrixA, int[][] matrixB, int size, int threadsCount, Result result) throws InterruptedException {
        int stripeWidth = size/threadsCount;
        int startIndex = 0;
        for(int i=0;i<size;i++){
            runIteration(matrixA,matrixB,stripeWidth,startIndex,result,i,size);
            if(startIndex==0) {
                startIndex = size - 1;
            }else{
                startIndex--;
            }
        }
    }

    private static void runIteration(int[][] matrixA, int[][] matrixB, int stripeWidth, int startIndex, Result result, int iteration, int size) {
//        System.out.println("s width:" + stripeWidth);

        for(int i = 0; i<size;i++){
            RowOrColumn[] rows = new RowOrColumn[stripeWidth];
            RowOrColumn[] columns = new RowOrColumn[stripeWidth];
            for(int j = 0;j<stripeWidth;j++){

                rows[j] = new RowOrColumn(matrixA[i],i);
//                System.out.println("start index: " + startIndex);
                columns[j] = new RowOrColumn(getColumn(matrixB,startIndex),startIndex);
                if(startIndex==size-1) {
                    startIndex = 0;
                }else{
                    startIndex++;
                }
            }

            MultThread thread = new MultThread(rows,columns,result,iteration);
            thread.start();
        }
    }

    private static int[] getColumn(int[][] matrix, int index){
        int[] column = new int[matrix[0].length];
        for(int i = 0;i<matrix[0].length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(j==index){
                    column[i] = matrix[i][j];
                }
            }
        }
        return column;
    }

    private static void multiplyMatrixesSerial(int[][] matrixA, int[][] matrixB, Result result){
        for(int i = 0; i<matrixA[0].length;i++){
            for(int j=0;j<matrixA[0].length;j++){
                result.resultMatrix[i][j] = multiplyRowOnColumn(matrixA[i],getColumn(matrixB,j));
            }
        }
    }

    private static int multiplyRowOnColumn(int[] row, int[] column){
        int sum = 0;
        for(int i=0;i<row.length;i++){
            sum+=row[i]*column[i];
        }
        return sum;
    }
}
