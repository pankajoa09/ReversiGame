


import java.util.ArrayList;

/**
 * Created by pjoa09 on 9/16/17.
 */
public class Controller {


    private Board board = new Board();
    private Block[][] grid = board.getGrid();
    private Statistics stats = new Statistics();
    private boolean turn;




    public void newGame() {
        Block[][] grid = board.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Block empty = new Block();
                empty.setAll(i, j, "Empty");
                board.addBlock(empty);
            }
        }

        Block white1 = new Block();
        white1.setAll(3, 3, "White");
        Block white2 = new Block();
        white2.setAll(4, 4, "White");
        Block black1 = new Block();
        black1.setAll(3, 4, "Black");
        Block black2 = new Block();
        black2.setAll(4, 3, "Black");
        board.addBlock(white1);
        board.addBlock(white2);
        board.addBlock(black1);
        board.addBlock(black2);

        board.setGrid(grid);
    }

    public void clickHandler(int i, int j) {

        Block clicked = new Block();
        clicked.setAll(i,j,grid[i][j].getType());
        stats.setTurn("Black");
        System.out.println(stats.getTurn());
        if (clicked.getType().equals("Empty")){
            if (stats.getTurn().equals("White")){
                whiteTurn(clicked);
                updateStats();
                stats.setTurn("Black");
            }
            else if (stats.getTurn().equals("Black")){
                blackTurn(clicked);
                updateStats();
                stats.setTurn("White");


            }
        }
    }

    private void whiteTurn(Block block){
        mechanics(block,"White");
    }

    private void blackTurn(Block block){
        mechanics(block,"Black");
    }

    private void mechanics(Block block,String turn){
        String toKill = "";
        if (turn.equals("Black")){
            toKill = "White";
        }
        else if (turn.equals("White")){
            toKill = "Black";
        }
        Block[] surr = getSurrounding(block);
        for (int i = 0;i<surr.length;i++){
            Block nextOne = surr[i];
            // if a block is white
            if (nextOne.getType().equals(toKill)){
                // see whats further down that road and round em up
                ArrayList<Block> firingSquad = roundUpTheNazis(nextOne,i,toKill);
                //if the list came out empty means we couldnt kill em
                if (!firingSquad.isEmpty()){
                    //if at the end there was a black this list would have something in it.
                    //then kill em or convert em tbh
                    killTheNazis(firingSquad,turn);
                    //then kill the new one too
                    block.setType(turn);
                    board.addBlock(block);
                }
            }

        }
    }

    private void killTheNazis(ArrayList<Block> nazis,String turn){
        for (int i = 0; i < nazis.size(); i++){
            Block curr = nazis.get(i);
            curr.setType(turn);
            board.addBlock(curr);
        }
    }

    private ArrayList<Block> roundUpTheNazis(Block firstNazi, int direction, String toKill){
        System.out.println("toKill: "+toKill);
        ArrayList<Block> naziJail = new ArrayList<>();
        naziJail.add(firstNazi);
        int d = direction;
        Block nextNazi;
        nextNazi = getSurrounding(firstNazi)[d];
        while (nextNazi.getType().equals(toKill)) {
            nextNazi = getSurrounding(firstNazi)[d];
            if (nextNazi.getType().equals(toKill)) {
                naziJail.add(nextNazi);
            }
            if (nextNazi.getType().equals("OutBound") || nextNazi.getType().equals("Empty")){
                naziJail.clear();
            }
        }
        return naziJail;
    }






    private Block tempNullCatcher(int i,int j){
        Block newBlock = new Block();
        if ((i>=0 && i<=grid.length-1) && (j>=0 && j<=grid.length-1)) {
            //we give a go
            newBlock = grid[i][j];
        }
        else{
            newBlock.setAll(i,j,"OutBound");
            //null it casually.
        }
        return newBlock;

    }

    private Block[] getSurrounding(Block selected){
        int currI = selected.getI();
        int currJ = selected.getJ();
        Block[] surroundings = new Block[8];

        //its clockwise surroundings so the first one is north and the next is north east etc.
        surroundings[0] = tempNullCatcher(currI-1,currJ+0);
        surroundings[1] = tempNullCatcher(currI-1,currJ+1);
        surroundings[2] = tempNullCatcher(currI+0,currJ+1);
        surroundings[3] = tempNullCatcher(currI+1,currJ+1);
        surroundings[4] = tempNullCatcher(currI+1,currJ+0);
        surroundings[5] = tempNullCatcher(currI+1,currJ-1);
        surroundings[6] = tempNullCatcher(currI+0,currJ-1);
        surroundings[7] = tempNullCatcher(currI-1,currJ-1);
        return surroundings;
    }




    public Block[][] getGameBoard(){
        return grid;
    }

    public void updateStats() {
        int black = 0;
        int white = 0;
        int empty = 0;
        Block[][] grid = board.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Block curr = grid[i][j];
                if (curr.getType().equals("White")) {
                    white++;
                } else if (curr.getType().equals("Black")) {
                    black++;
                } else if (curr.getType().equals("Empty")) {
                    empty++;
                } else {
                    throw new IllegalArgumentException("Something's up: There is a space that isn't White,Black,or Empty");
                }
            }
        }
        stats.setBlackScore(black);
        stats.setWhiteScore(white);
        stats.setEmptyScore(empty);





    }


}
