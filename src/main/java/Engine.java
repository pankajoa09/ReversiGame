import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pjoa09 on 9/24/17.
 */
public class Engine {
    Debug debug = new Debug();


    public boolean isPermissible(Block block, String turn, Board board) {
        //If there are no victims then move and block is not empty then not valid
        ArrayList<ArrayList<Block>> victims = getVictims(block,turn,board);
        return (!victims.isEmpty()) && (block.getType().equals("Empty"));

    }


    public Board start(Block block, String turn, final Board oldBoard) {
        Board board = new Board();
        board.createBoard();
        board.copyBoard(oldBoard);
        ArrayList<ArrayList<Block>> victims = getVictims(block, turn, board);
        board = executeVictims(turn, block,victims, board);

        return board;
    }


    public ArrayList<ArrayList<Block>> getVictims(Block block, String turn, final Board board) {
        String toKill = "";
        if (turn.equals("Black")) {
            toKill = "White";
        } else if (turn.equals("White")) {
            toKill = "Black";
        }
        ArrayList<ArrayList<Block>> groupOfFiringSquad = new ArrayList<ArrayList<Block>>();

        Block[] surr = seeSurroundingLand(block, board);
        for (int i = 0; i < surr.length; i++) {
            Block nextOne = surr[i];
            // if a block is white
            if (nextOne.getType().equals(toKill)) {
                // see whats further down that road and round em up


                ArrayList<Block> firingSquad = roundUpTheNazis(block, i, toKill, turn, board);
                //if the list came out empty means we couldnt kill em

                if (!firingSquad.isEmpty()) {
                    groupOfFiringSquad.add(firingSquad);
                }

            }

        }

        return groupOfFiringSquad;
    }


    private Board executeVictims(String turn, Block block, ArrayList<ArrayList<Block>> groupOfFiringSquad,Board board) {

        if (!groupOfFiringSquad.isEmpty()) {
            for (int it = 0; it < groupOfFiringSquad.size(); it++) {
                ArrayList<Block> firingSquad = groupOfFiringSquad.get(it);

                //if at the end there was a black this list would have something in it.
                //then kill em or convert em tbh
                board = killTheNazis(firingSquad, turn,board);
                //then kill the new one too
                block.setType(turn);
                board.addBlock(block);


            }
        }
        return board;
    }

    private Board killTheNazis(ArrayList<Block> nazis,String turn,Board board){
        for (int i = 0; i < nazis.size(); i++){
            Block curr = nazis.get(i);
            curr.setAll(curr.getI(),curr.getJ(),turn);
            board.addBlock(curr);


        }
        return board;
    }

    private ArrayList<Block> roundUpTheNazis(Block firstNazi, int direction, String toKill, String turn, Board baard){
        ArrayList<Block> naziJail = new ArrayList<Block>();

        int d = direction;
        Block nextNazi;
        nextNazi = seeSurroundingLand(firstNazi,baard)[d];

        while (true) {

            if (nextNazi.getType().equals("OutBound") || nextNazi.getType().equals("Empty")){
                naziJail.clear();
                break;
            }
            else if (nextNazi.getType().equals(turn)){
                break;
            }

            else if (nextNazi.getType().equals(toKill)) {
                naziJail.add(nextNazi);
            }

            nextNazi = seeSurroundingLand(nextNazi,baard)[d];
        }

        return naziJail;
    }


    private Block[] seeSurroundingLand(Block selected,Board baard){
        int currI = selected.getI();
        int currJ = selected.getJ();
        Block[] surroundings = new Block[8];

        //its clockwise surroundings so the first one is north and the next is north east etc.
        surroundings[0] = getBlock(currI-1,currJ+0,baard);
        surroundings[1] = getBlock(currI-1,currJ+1,baard);
        surroundings[2] = getBlock(currI+0,currJ+1,baard);
        surroundings[3] = getBlock(currI+1,currJ+1,baard);
        surroundings[4] = getBlock(currI+1,currJ+0,baard);
        surroundings[5] = getBlock(currI+1,currJ-1,baard);
        surroundings[6] = getBlock(currI+0,currJ-1,baard);
        surroundings[7] = getBlock(currI-1,currJ-1,baard);
        return surroundings;
    }


    private Block getBlock(int i,int j, Board baard){
        Block newBlock = new Block();
        if ((i>=0 && i<=baard.getSize()-1) && (j>=0 && j<=baard.getSize()-1)) {
            //we give a go
            newBlock = baard.getBlockFromGrid(i,j);
        }
        else{
            newBlock.setAll(i,j,"OutBound");
            //null it casually.
        }
        return newBlock;

    }
}
