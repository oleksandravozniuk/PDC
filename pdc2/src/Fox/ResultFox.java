package Fox;

public class ResultFox {
    public int[][] resultMatrix;

    public ResultFox(int size){
        resultMatrix = new int[size][size];
    }

    public synchronized void setResultMatrix(int[][] blockResult, int x, int y){
        for(int i=0;i<blockResult.length;i++){
            for(int j=0;j<blockResult.length;j++){
                resultMatrix[x][y++]+=blockResult[i][j];
            }
            x++;
        }
        //printMatrix();
        System.out.println("mult value" + blockResult[0][0]);
    }

    public synchronized void printMatrix(){
        for(int i =0;i<resultMatrix[0].length;i++){
            for (int j=0;j<resultMatrix[0].length;j++){
                System.out.print(resultMatrix[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }
}
