import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pjoa09 on 9/20/17.
 */
public class Robot {



    public Block choice(Block[][] theirGrid){
        RobotBoard baard = new RobotBoard();
        Block[][] ourGrid = baard.getGrid();
        for (int i=0; i<theirGrid.length;i++){
            for (int j=0; j<theirGrid.length;j++){
                Block block = new Block();
                block.setAll(theirGrid[i][j].getI(),theirGrid[i][j].getJ(),theirGrid[i][j].getType());
                baard.addBlock(block);
            }
        }

        Random rand = new Random();
        boolean permissible = false;
        int i;
        int j;
        Block block = new Block();
        System.out.println("=======AI========");
        while (true) {
            i = rand.nextInt(7);
            j = rand.nextInt(7);
            System.out.println(i + " "+j);
            block = baard.getBlockFromGrid(i,j);
            permissible = mechanics(block,"White",baard);
            if (permissible){
                break;
            }
        }
        System.out.println("========");
        View view = new View();
        view.refreshGrid();
        return block;
    }


    private boolean mechanics(Block block,String turn,RobotBoard baard){
        boolean permissible = false;
        String toKill = "";
        if (turn.equals("Black")){
            toKill = "White";
        }
        else if (turn.equals("White")){
            toKill = "Black";
        }
        ArrayList<ArrayList<Block>> groupOfFiringSquad = new ArrayList<>();

        Block[] surr = seeSurroundingLand(block,baard);
        for (int i = 0;i<surr.length;i++){
            Block nextOne = surr[i];
            // if a block is white
            if (nextOne.getType().equals(toKill)){
                // see whats further down that road and round em up


                ArrayList<Block> firingSquad = roundUpTheNazis(block,i,toKill,turn,baard);
                //if the list came out empty means we couldnt kill em

                if (!firingSquad.isEmpty()) {
                    groupOfFiringSquad.add(firingSquad);
                }

            }

        }
        if (!groupOfFiringSquad.isEmpty()) {
            for (int it = 0; it < groupOfFiringSquad.size(); it++) {
                ArrayList<Block> firingSquad = groupOfFiringSquad.get(it);

                //if at the end there was a black this list would have something in it.
                //then kill em or convert em tbh
                killTheNazis(firingSquad, turn,baard);
                //then kill the new one too
                block.setType(turn);
                baard.addBlock(block);
                permissible=true;
            }
        }
        return permissible;
    }

    private void killTheNazis(ArrayList<Block> nazis,String turn,RobotBoard baard){


        for (int i = 0; i < nazis.size(); i++){
            Block curr = nazis.get(i);
            curr.setAll(curr.getI(),curr.getJ(),turn);
            baard.addBlock(curr);
        }
    }

    private ArrayList<Block> roundUpTheNazis(Block firstNazi, int direction, String toKill, String turn, RobotBoard baard){
        ArrayList<Block> naziJail = new ArrayList<>();

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


    private Block[] seeSurroundingLand(Block selected,RobotBoard baard){
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


    private Block getBlock(int i,int j, RobotBoard baard){
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
