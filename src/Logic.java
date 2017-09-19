import java.util.ArrayList;

/**
 * Created by pjoa09 on 9/20/17.
 */
public class Logic extends Controller {

    private boolean mechanics(Block block,String turn){
        boolean permissible = false;
        String toKill = "";
        if (turn.equals("Black")){
            toKill = "White";
        }
        else if (turn.equals("White")){
            toKill = "Black";
        }
        ArrayList<ArrayList<Block>> groupOfFiringSquad = new ArrayList<>();
        System.out.println("toKill: "+toKill);
        Block[] surr = getSurrounding(block);
        for (int i = 0;i<surr.length;i++){
            Block nextOne = surr[i];
            // if a block is white
            if (nextOne.getType().equals(toKill)){
                // see whats further down that road and round em up
                System.out.println("=====");

                ArrayList<Block> firingSquad = roundUpTheNazis(block,i,toKill,turn);
                //if the list came out empty means we couldnt kill em
                for (int s=0;s<firingSquad.size();s++){
                    System.out.println(firingSquad.get(s));
                }
                if (!firingSquad.isEmpty()) {
                    groupOfFiringSquad.add(firingSquad);
                }

            }

        }
        if (!groupOfFiringSquad.isEmpty()) {
            for (int it = 0; it < groupOfFiringSquad.size(); it++) {
                ArrayList<Block> firingSquad = groupOfFiringSquad.get(it);
                System.out.println("LEGIT!!");
                //if at the end there was a black this list would have something in it.
                //then kill em or convert em tbh
                killTheNazis(firingSquad, turn);
                //then kill the new one too
                block.setType(turn);
                board.addBlock(block);
                permissible=true;
            }
        }
        return permissible;
    }

    private void killTheNazis(ArrayList<Block> nazis,String turn){
        System.out.println("killthenazis conver them to"+turn);
        for (int i = 0; i < nazis.size(); i++){
            Block curr = nazis.get(i);
            curr.setAll(curr.getI(),curr.getJ(),turn);
            board.addBlock(curr);
            System.out.println(curr.getType());
        }
    }

    private ArrayList<Block> roundUpTheNazis(Block firstNazi, int direction, String toKill, String turn){
        ArrayList<Block> naziJail = new ArrayList<>();
        System.out.println("firstNazi: "+firstNazi.getI()+" "+firstNazi.getJ()+firstNazi.getType());
        int d = direction;
        Block nextNazi;
        nextNazi = getSurrounding(firstNazi)[d];
        System.out.println("round up nazis");
        System.out.println(d);
        while (true) {
            System.out.println("nextNazi: "+nextNazi.getI()+" "+nextNazi.getJ()+nextNazi.getType());
            if (nextNazi.getType().equals("OutBound") || nextNazi.getType().equals("Empty")){
                System.out.println("list cleared because NOT LEGIT");
                naziJail.clear();
                break;
            }
            else if (nextNazi.getType().equals(turn)){
                System.out.println("we good here found an officer");
                break;
            }

            else if (nextNazi.getType().equals(toKill)) {
                System.out.println("we added that");
                naziJail.add(nextNazi);
            }

            nextNazi = getSurrounding(nextNazi)[d];
        }

        return naziJail;
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


    private Block tempNullCatcher(int i,int j){
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
