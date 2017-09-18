/**
 * Created by pjoa09 on 9/16/17.
 */
public class Board {

    public Block[][] getGrid() {
        return grid;
    }

    public void setGrid(Block[][] board) {
        Board.grid = board;
    }

    private static Block grid[][] = new Block[8][8];

    public void addBlock(Block block){
        grid[block.getI()][block.getJ()] = block;
    }




}
