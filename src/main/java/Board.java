/**
 * Created by pjoa09 on 9/16/17.
 */
public class Board {


    public Block[][] getGrid() {
        return grid;
    }

    public void setGrid(Block[][] board) {
        this.grid = board;
    }

    private Block grid[][];



    public void addBlock(Block block){
        Block newBlock = new Block();
        newBlock.setAll(block.getI(),block.getJ(),block.getType());
        this.grid[block.getI()][block.getJ()] = newBlock;
    }

    public int getSize(){
        return this.grid.length;
    }

    public Block getBlockFromGrid(int i, int j){

        return this.grid[i][j];
    }

    public void createBoard() {
        this.grid = new Block[8][8];
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                Block block = new Block();
                block.setAll(i,j,"Empty");
                this.addBlock(block);
            }
        }

    }

    public void copyBoard(Board oldBoard){

        Debug debug = new Debug();


        for (int i=0; i<oldBoard.getSize(); i++){
            for (int j=0; j<oldBoard.getSize(); j++){
                Block block = oldBoard.getBlockFromGrid(i,j);
                Block newBlock = new Block();
                newBlock.setAll(block.getI(),block.getJ(),block.getType());
                this.addBlock(newBlock);
            }
        }



    }




}
