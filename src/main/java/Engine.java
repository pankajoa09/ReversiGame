import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pjoa09 on 9/24/17.
 */
public class Engine {
    Debug debug = new Debug();


    public boolean isPermissible(Block block, String turn, Board board) {
        //If there are no victims then move and block is not empty then not valid
        ArrayList<ArrayList<Block>> listOfDisksToFlip = getAllDisksToFlip(block,turn,board);
        return (!listOfDisksToFlip.isEmpty()) && (block.getType().equals("Empty"));

    }


    public Board start(Block block, String turn, final Board oldBoard) {
        Board board = new Board();
        board.createBoard();
        board.copyBoard(oldBoard);
        ArrayList<ArrayList<Block>> victims = getAllDisksToFlip(block, turn, board);
        board = flipAllDisks(turn, block,victims, board);

        return board;
    }


    public ArrayList<ArrayList<Block>> getAllDisksToFlip(Block block, String turn, Board board) {
        String toFlip = "";
        if (turn.equals("Black")) {
            toFlip = "White";
        } else if (turn.equals("White")) {
            toFlip = "Black";
        }
        ArrayList<ArrayList<Block>> bigListOfOpposers = new ArrayList<ArrayList<Block>>();

        Block[] surrounding = seeSurroundingBlocks(block, board);
        for (int direction = 0; direction < surrounding.length; direction++) {
            Block nextOne = surrounding[direction];
            // if a block is white
            if (nextOne.getType().equals(toFlip)) {
                // see whats further down that road and round em up

                ArrayList<Block> smallListOfOpposers = getDisksToFlip(block, direction, toFlip, turn, board);
                //if the list came out empty means we couldnt kill em

                if (!smallListOfOpposers.isEmpty()) {
                    bigListOfOpposers.add(smallListOfOpposers);
                }

            }

        }

        return bigListOfOpposers;
    }


    private Board flipAllDisks(String turn, Block block, ArrayList<ArrayList<Block>> bigListOfDisksToFlip,Board board) {

        if (!bigListOfDisksToFlip.isEmpty()) {
            for (int it = 0; it < bigListOfDisksToFlip.size(); it++) {
                ArrayList<Block> smallListOFDisksToFlip = bigListOfDisksToFlip.get(it);

                //if at the end there was a black this list would have something in it.
                //then kill em or convert em tbh
                board = flipDisks(smallListOFDisksToFlip, turn,board);
                //then kill the new one too
                block.setType(turn);
                board.addBlock(block);


            }
        }
        return board;
    }

    private Board flipDisks(ArrayList<Block> smallListOfDisksToFlip,String turn,Board board){
        for (int i = 0; i < smallListOfDisksToFlip.size(); i++){
            Block curr = smallListOfDisksToFlip.get(i);
            curr.setAll(curr.getI(),curr.getJ(),turn);
            board.addBlock(curr);


        }
        return board;
    }

    private ArrayList<Block> getDisksToFlip(Block firstNazi, int direction, String toFlip, String turn, Board board){
        ArrayList<Block> listOfOpposers = new ArrayList<Block>();

        Block nextOpposer;
        nextOpposer = seeSurroundingBlocks(firstNazi,board)[direction];

        while (true) {

            if (nextOpposer.getType().equals("OutBound") || nextOpposer.getType().equals("Empty")){
                listOfOpposers.clear();
                break;
            }
            else if (nextOpposer.getType().equals(turn)){
                break;
            }

            else if (nextOpposer.getType().equals(toFlip)) {
                listOfOpposers.add(nextOpposer);
            }

            nextOpposer = seeSurroundingBlocks(nextOpposer,board)[direction];
        }

        return listOfOpposers;
    }


    private Block[] seeSurroundingBlocks(Block selected,Board board){
        int currI = selected.getI();
        int currJ = selected.getJ();
        Block[] surroundings = new Block[8];

        //its clockwise surroundings so the first one is north and the next is north east etc.
        surroundings[0] = getBlock(currI-1,currJ+0,board);
        surroundings[1] = getBlock(currI-1,currJ+1,board);
        surroundings[2] = getBlock(currI+0,currJ+1,board);
        surroundings[3] = getBlock(currI+1,currJ+1,board);
        surroundings[4] = getBlock(currI+1,currJ+0,board);
        surroundings[5] = getBlock(currI+1,currJ-1,board);
        surroundings[6] = getBlock(currI+0,currJ-1,board);
        surroundings[7] = getBlock(currI-1,currJ-1,board);
        return surroundings;
    }


    private Block getBlock(int i,int j, Board board){
        Block newBlock = new Block();
        if ((i>=0 && i<=board.getSize()-1) && (j>=0 && j<=board.getSize()-1)) {
            //we give a go
            newBlock = board.getBlockFromGrid(i,j);
        }
        else{
            newBlock.setAll(i,j,"OutBound");
            //null it casually.
        }
        return newBlock;

    }
}
