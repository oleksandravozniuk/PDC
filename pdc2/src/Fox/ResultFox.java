package Fox;

public class ResultFox {
    public int[][] resultMatrix;
    public int[] iterations;
    public int blocksInRow;

    public ResultFox(int size, int blocksInRow){
        resultMatrix = new int[size][size];
        iterations = new int[blocksInRow];
        this.blocksInRow = blocksInRow;
    }

    public synchronized void setResultMatrix(int[][] blockResult, int x, int y, int iteration) throws InterruptedException {
        while(checkIteration(iteration)){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int[] ints : blockResult) {
            int columnIndex = y;
            for (int j = 0; j < blockResult.length; j++) {
                resultMatrix[x][columnIndex++] += ints[j];
            }
            x++;
        }
        iterations[iteration]++;
        notify();
        //printMatrix();
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

    private synchronized boolean checkIteration(int it){
        for(int i=0;i<blocksInRow;i++){
            if(i<it && iterations[i] < blocksInRow){
                return true;
            }
        }
        return false;
    }
}
