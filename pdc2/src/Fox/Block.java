package Fox;

public class Block {
    public int[][] matrix;
    public Block next;
    int x;
    int y;

    public Block(int[][] matrix, int x, int y)
    {
        this.matrix = matrix;
        this.x = x;
        this.y = y;
    }
}
