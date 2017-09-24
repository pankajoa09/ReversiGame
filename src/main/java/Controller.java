



import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by pjoa09 on 9/16/17.
 */
public class Controller {




    Statistics stats = new Statistics();
    View view = new View();
    Debug debug = new Debug();

    Robot robot = new Robot();
    Engine engine = new Engine();





    public void startApplication(Stage primaryStage){
        Board board = newGame();
        stats.setMode("Human");
        view.initializeStage(primaryStage,board.getGrid(),stats);

    }

    public Board newGame() {
        //Board board = new Board();
        Board board = new Board();
        board.createBoard();
        stats.setTurn("Black");

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
        //we refresh the gui

        refreshView(board);
        //then pass on the grid to the gridStorage
        board.setGrid(board.getGrid());

        return board;

    }

    public void resetClickHandler(){
        stats.setMode("Human");
        Board board = newGame();
        updateStats(board);

    }

    public void robotClickHandler(){
        stats.setMode("Robot");
        Board board = newGame();
        updateStats(board);

    }


    public void rectangleClickHandler(int i, int j, Block[][] grid) {

        Board board = new Board();
        board.setGrid(grid);

        Block clicked = board.getBlockFromGrid(i,j);

        //stats.setTurn("Black")
        if (clicked.getType().equals("Empty")) {
            if (stats.getTurn().equals("White")) {

                whiteTurn(clicked, board);
            }
            else if (stats.getTurn().equals("Black")){
                blackTurn(clicked,board);
            }
        }



    }





    private Board whiteTurn(Block block,Board board){
        boolean isPermissible = engine.isPermissible(block,"White",board);
        if (isPermissible) {

            board = engine.start(block,"White",board);
            stats.setTurn("Black");
            refreshView(board);
        }
        else{
            System.out.println("Not permissible");
        }
        return board;
    }



    private Board blackTurn(Block block,Board board){


        boolean isPermissible = engine.isPermissible(block,"Black",board);
        if (isPermissible) {
            board = engine.start(block,"Black",board);
            stats.setTurn("White");
            refreshView(board);
            if (stats.getMode().equals("Robot")) {
                Robot robot = new Robot();
                Block robotChoice = robot.choice(board.getGrid(),"White");
                rectangleClickHandler(robotChoice.getI(),robotChoice.getJ(),board.getGrid());


            }
        }


        return board;
    }






    public void updateStats(Board board) {
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
        if (black > white){
            stats.setCurrentWinner("Black");
        }
        else if (black < white){
            stats.setCurrentWinner("White");
        }
        else{
            stats.setCurrentWinner("Draw");
        }
        stats.setBlackScore(black);
        stats.setWhiteScore(white);
        stats.setEmptyScore(empty);


    }

    public void refreshView(Board board){

        updateStats(board);
        view.refreshGrid(board.getGrid());
        view.refreshBorder(stats);
    }





    }



