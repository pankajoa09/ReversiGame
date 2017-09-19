/**
 * Created by pjoa09 on 9/20/17.
 */
public class RobotBoard {

    public Block[][] getGrid() {
        return grid;
    }

    public void setGrid(Block[][] board) {
        this.grid = board;
    }

    public Block grid[][] = new Block[8][8];

    public void addBlock(Block block){
        this.grid[block.getI()][block.getJ()] = block;
    }

    public int getSize(){
        return grid.length;
    }

    public Block getBlockFromGrid(int i, int j){
        Block block = grid[i][j];
        return block;
    }


}
