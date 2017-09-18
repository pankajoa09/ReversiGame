/**
 * Created by pjoa09 on 9/11/17.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;


public class Main extends Application{






    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Reversi");
        Controller controller = new Controller();
        controller.newGame();
        View view = new View();
        GridPane gridPane = view.refreshGrid();
        Scene scene = new Scene(gridPane, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.show();

    }





    /*
    public void refreshBoard(int[][] board,final boolean turn){

        Image emptyTile = new Image("ReversiEmptyTile.png");
        Image whiteTile = new Image("ReversiWhiteTile.png");
        Image blackTile = new Image("ReversiBlackTile.png");

        for (int i=0;i < 8;i++) {
            for (int j=0; j < 8; j++) {

                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(80);
                rectangle.setWidth(80);
                final int x = j;
                final int y = i;

                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t){
                        Space click = new Space();
                        click.set(x,y);
                        int[][] newBoard = eachTurnBoard(click,turn,board);
                        boolean whiteTurn = turn;
                        whiteTurn = !whiteTurn;
                        refreshBoard(newBoard,whiteTurn);

                    }
                });
                if (board[i][j] == 0){
                    rectangle.setFill(new ImagePattern(emptyTile));
                }
                else if (board[i][j] == 1){
                    rectangle.setFill(new ImagePattern(whiteTile));
                }
                else if (board[i][j] == 2){
                    rectangle.setFill((new ImagePattern(blackTile)));
                }


                gridPane.setRowIndex(rectangle,i);
                gridPane.setColumnIndex(rectangle,j);
                gridPane.getChildren().addAll(rectangle);

            }



        }
    }

    public int[][] emptyBoard(){
        int[][] emptyBoard = new int[8][8];
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                emptyBoard[i][j] = 0;
            }
        }

        return emptyBoard;
    }

    public int[][] newGameBoard(){
        int[][] newGameBoard = emptyBoard();
        newGameBoard[3][3] = 1;
        newGameBoard[3][4] = 2;
        newGameBoard[4][3] = 2;
        newGameBoard[4][4] = 1;
        return newGameBoard;
    }

    //clicked Where the player clicked
    //turn whos turn it was
    //board what is the current state of the board
    public int[][] eachTurnBoard(Space click,boolean turn,int[][] board){
        System.out.println("x: "+click.getX());
        System.out.println("y: "+click.getY());
        System.out.println("turn: "+turn);
        if (turn == false){

            //then its blacks turn, or the 2s
            System.out.println("BLACKS TURN");
            board = mechanicsBlack(board,click);


        }
        else{
            //its whites turn, or the 1s.

        }
        return board;
    }

    public Space[] getSurrounding(Space space){
        Space[] directions = new Space[8];
        directions[0] = space.getOnNorth();
        directions[1] = space.getOnNorthEast();
        directions[2] = space.getOnEast();
        directions[3] = space.getOnSouthEast();
        directions[4] = space.getOnSouth();
        directions[5] = space.getOnSouthWest();
        directions[6] = space.getOnWest();
        directions[7] = space.getOnNorthWest();
        return directions;
    }

    public int[][] mechanicsBlack(int[][] board,Space selected){

        //check if coordinates are on an empty space

        if (getState(selected,board) == 0) {
            Space[] level1 = getSurrounding(selected);


            for (int i=0; i<=level1.length;i++) {
                //check if there is a white next to it
                if (getState(level1[i],board) == WHITE){
                    //if there is, see whats further down that track
                    Space[] level2 = getSurrounding(level1[i]);
                    if (getState(level2[i],board) == BLACK) {
                        changeState(level1[i],board,BLACK);
                    }

                    }
                }
            }

            //if there isn't the move is not permissible.

            //if black: move is permissible, flip the one in the middle as black
            //if white: check further
            //if green: not permissible

        return board;
    }

    public int getState(Space coo, int[][] board){
        int x = coo.getX();
        int y = coo.getY();
        return board[y][x];
    }

    public int[][] changeState(Space coo, int[][] board, int state) {
        int x = coo.getX();
        int y = coo.getY();
        if (state==0) {
            board[y][x] = 0;
        }
        else if (state==1){
            board[y][x] = 1;
        }
        else if (state==2) {
            board[y][x] = 2;
        }
        else {
            System.out.println("======================");
            System.out.println("The integer for state provided was not between 0-2");
            System.out.println("0 is empty");
            System.out.println("1 is white");
            System.out.println("2 is black");
            System.out.println("======================");
            board[y][x] = -1;
        }
        return board;
    }
    */








    }





