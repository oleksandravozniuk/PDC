package Fox;

public class FoxThread extends Thread
{
    public Block A;
    public Block B;
    public int blocksInRow;
    ResultFox result;
    private int blockSize;
    int x;
    int y;

    public FoxThread(Block A, Block B, int blocksInRow, ResultFox result, int blockSize, int x, int y)
    {
        this.A = A;
        this.B = B;
        this.blocksInRow = blocksInRow;
        this.result = result;
        this.blockSize = blockSize;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run()
    {
        var curA = A;
        var curB = B;
        for(int i=0;i<blocksInRow;i++){
            int[][] multResult = new int[blockSize][blockSize];
            for(int j = 0;j<blockSize;j++){
                for(int h=0;h<blockSize;h++){
                    var columnB = getColumn(curB.matrix, h);
                    var sum = 0;
                    for (int k=0;k<blockSize;k++){
                        sum += curA.matrix[j][k]*columnB[k];
                    }
                    multResult[j][h] = sum;
                }
            }
            try {
                result.setResultMatrix(multResult,x,y,i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curA=A.next;
            curB=B.next;
        }

    }

    private int[] getColumn(int[][] matrix, int index){
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
}