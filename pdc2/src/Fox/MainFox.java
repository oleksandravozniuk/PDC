package Fox;

public class MainFox {
    public static void main(String[] args) throws InterruptedException {
        int size = 4;
        int blocksInRow = 4;
        var a = createMatrix(size);
        var b = createMatrix2(size);
        var result = new ResultFox(size);
        multiplyAsync(result,a,b,size,blocksInRow);
    }

    private static void multiplyAsync(ResultFox result, int[][] a, int[][] b, int size, int blocksInRow) throws InterruptedException {
        System.out.println("a " + a[0][2]);
        int blockSize = size/blocksInRow;
        var aBlocks = divideOnBlocks(a,blockSize,blocksInRow);
        var bBlocks = divideOnBlocks(b,blockSize,blocksInRow);
        setRelationsForA(aBlocks,blocksInRow);
        setRelationsForB(bBlocks,blocksInRow);
        System.out.println("block v " + aBlocks[0][2].matrix[0][0]);
        for(int i = 0;i<blocksInRow;i++){
            for(int j=0;j<blocksInRow;j++){
                FoxThread foxThread = new FoxThread(aBlocks[i][j],bBlocks[i][j],blocksInRow,result,blockSize,i,j);
                foxThread.start();
//                foxThread.join();
            }
        }
        result.printMatrix();
    }

    private static Block[][] divideOnBlocks(int[][] matrix, int blockSize, int blocksInRow){
        System.out.println("jj"+matrix[0][2]);
        var indexRow = 0;
        int indexColumn = 0;
        var blocks = new Block[blocksInRow][blocksInRow];
        for(int i=0;i<blocksInRow;i++){
            for(int j=0;j<blocksInRow;j++){
                var matrixBlock = new int[blockSize][blockSize];
                indexRow=i*blockSize;
                for(int k=0;k<blockSize;k++){
                    for(int h =0;h<blockSize;h++){
                        //System.out.println(matrix[indexRow][indexColumn++]);
                        matrixBlock[k][h] = matrix[indexRow][indexColumn++];
                    }
                    indexColumn = 0;
                    indexRow++;
                }
                blocks[i][j] = new Block(matrixBlock, i, j);
            }
        }

         return blocks;
    }

    private static void setRelationsForA(Block[][] a, int blocksInRow){
        //set blocks in right order
        var newA = new Block[blocksInRow][blocksInRow];
        for(int i=0;i<blocksInRow;i++){
            int columnIndex = i;
            for(int j=0;j<blocksInRow;j++){
                newA[i][j] = a[i][columnIndex];
                if(columnIndex==blocksInRow-1){
                    columnIndex = 0;
                }else{
                    columnIndex++;
                }
            }
        }
        for(int i = 0; i<blocksInRow; i++){
            for(int j=0;j<blocksInRow;j++){
                if(j==blocksInRow-1){
                    a[i][j].next = a[i][0];
                }else{
                    a[i][j].next = a[i][j+1];
                }
            }
        }
    }

    private static void setRelationsForB(Block[][] b, int blocksInRow){
        //set blocks in right order
        var newB = new Block[blocksInRow][blocksInRow];
        for(int i=0;i<blocksInRow;i++){
            int rowIndex = i;
            for(int j=0;j<blocksInRow;j++){
                newB[i][j] = b[rowIndex][j];
                if(rowIndex==blocksInRow-1){
                    rowIndex=0;
                }
            }
        }
        for(int i = 0; i<blocksInRow; i++){
            for(int j=0;j<blocksInRow;j++){
                if(i==blocksInRow-1){
                    b[i][j].next = b[0][j];
                }else{
                    b[i][j].next = b[i+1][j];
                }
            }
        }
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
}
