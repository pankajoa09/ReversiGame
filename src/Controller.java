


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pjoa09 on 9/16/17.
 */
public class Controller {


    Board board = new Board();
    Statistics stats = new Statistics();
    Debug debug = new Debug();






    public void newGame() {
        stats.setTurn("Black");
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
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

    }

    public void resetClickHandler(){
        newGame();
        updateStats();
    }


    public void rectangleClickHandler(int i, int j) {

        Block clicked = board.getBlockFromGrid(i,j);

        //stats.setTurn("Black");
        if (stats.getTurn().equals("White")){

            if (clicked.getType().equals("Empty")) {
                whiteTurn(clicked);
                updateStats();
            }
        }
        else if (stats.getTurn().equals("Black")){
            if (clicked.getType().equals("Empty")) {
                blackTurn(clicked);
                updateStats();
            }
        }
    }



    private void whiteTurn(Block block){



        boolean permissible = mechanics(block, "White");



        if (permissible) {
            System.out.println("THIS DIDNT EEN HAPPPENDKSJFLJD");
            stats.setTurn("Black");
            //View view = new View();
            //view.refreshGrid();
            //view.refreshBorder();

            debug.printGrid(board.getGrid(),stats.getTurn());
        }
    }



    private void blackTurn(Block block){

        boolean permissible = mechanics(block,"Black");
        if (permissible) {
            stats.setTurn("White");

            Board board = getCurrentBoard();
            Robot robot = new Robot();
            System.out.println("Before:");
            debug.printGrid(board.getGrid(),stats.getTurn());
            Block robotChoice = robot.choice(board.getGrid());
            System.out.println("After:");
            debug.printGrid(board.getGrid(),stats.getTurn());

            System.out.println("Robot Choice: "+robotChoice.getI()+" "+robotChoice.getJ());
            whiteTurn(robotChoice);

            //View view = new View();
            //view.refreshGrid();
            //view.refreshBorder();


        }
    }



    public void updateStats() {
        int black = 0;
        int white = 0;
        int empty = 0;

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Block curr = board.getBlockFromGrid(i, j);
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


    public Board getCurrentBoard(){
        return board;
    }

    public String getCurrentTurn(){
        updateStats();
        return stats.getTurn();
    }

    public int getCurrentBlackScore(){
        updateStats();

        return stats.getBlackScore();
    }

    public int getCurrentWhiteScore(){
        updateStats();

        return stats.getWhiteScore();
    }


    public String getCurrentWinner(){
        int white = getCurrentWhiteScore();
        int black = getCurrentBlackScore();
        String winner;
        if (white > black){
            winner = "White";
        }
        else if (white < black){
            winner = "Black";
        }
        else{
            winner = "Draw";
        }
        return winner;
    }




    public Block[][] getGameBoard(){
        return board.getGrid();
    }




        public boolean mechanics(Block block,String turn){
            boolean permissible = false;
            String toKill = "";
            if (turn.equals("Black")){
                toKill = "White";
            }
            else if (turn.equals("White")){
                toKill = "Black";
            }
            ArrayList<ArrayList<Block>> groupOfFiringSquad = new ArrayList<>();

            Block[] surr = seeSurroundingLand(block);
            for (int i = 0;i<surr.length;i++){
                Block nextOne = surr[i];
                // if a block is white
                if (nextOne.getType().equals(toKill)){
                    // see whats further down that road and round em up


                    ArrayList<Block> firingSquad = roundUpTheNazis(block,i,toKill,turn);
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
            nextNazi = seeSurroundingLand(firstNazi)[d];
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

                nextNazi = seeSurroundingLand(nextNazi)[d];
            }

            return naziJail;
        }


        private Block[] seeSurroundingLand(Block selected){
            int currI = selected.getI();
            int currJ = selected.getJ();
            Block[] surroundings = new Block[8];

            //its clockwise surroundings so the first one is north and the next is north east etc.
            surroundings[0] = getBlock(currI-1,currJ+0);
            surroundings[1] = getBlock(currI-1,currJ+1);
            surroundings[2] = getBlock(currI+0,currJ+1);
            surroundings[3] = getBlock(currI+1,currJ+1);
            surroundings[4] = getBlock(currI+1,currJ+0);
            surroundings[5] = getBlock(currI+1,currJ-1);
            surroundings[6] = getBlock(currI+0,currJ-1);
            surroundings[7] = getBlock(currI-1,currJ-1);
            return surroundings;
        }


        private Block getBlock(int i,int j){
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



