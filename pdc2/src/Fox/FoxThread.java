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
        int[][] multResult = new int[blockSize][blockSize];
        var curA = A;
        var curB = B;
        for(int i=0;i<blocksInRow;i++){
            for(int j = 0;j<blockSize;j++){
                for (int k=0;k<blockSize;k++){
                    multResult[j][k]=curA.matrix[j][k]*curB.matrix[j][k];
                }
            }
            curA=A.next;
            curB=B.next;
        }
        result.setResultMatrix(multResult,x,y);
    }
}