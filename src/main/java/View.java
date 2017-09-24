

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * Created by pjoa09 on 9/16/17.
 */
public class View {


    private static GridPane mainGridPane = new GridPane();
    private static StackPane stackPane = new StackPane();

    private static GridPane gridPane = new GridPane();
    private static BorderPane borderPane = new BorderPane();


    private Debug debug = new Debug();





    public GridPane refreshGrid(Block[][] grid) {

        gridPane.getChildren().clear();



        Image emptyTile = new Image(getClass().getResource("ReversiEmptyTile.png").toExternalForm());

        Image whiteTile = new Image("ReversiWhiteTile.png");
        Image blackTile = new Image("ReversiBlackTile.png");



        debug.printGrid(grid,"AT VIEW");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(80);
                rectangle.setWidth(80);
                final int I = i;
                final int J = j;
                if (grid[i][j].getType().equals("Empty")) {
                    rectangle.setFill(new ImagePattern(emptyTile));
                } else if (grid[i][j].getType().equals("White")) {
                    rectangle.setFill(new ImagePattern(whiteTile));
                } else if (grid[i][j].getType().equals("Black")) {
                    rectangle.setFill((new ImagePattern(blackTile)));
                }

                gridPane.setRowIndex(rectangle, i);
                gridPane.setColumnIndex(rectangle, j);
                gridPane.getChildren().addAll(rectangle);

                final Block[][] finalGrid = grid;

                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent t) {
                        Controller controller = new Controller();
                        controller.rectangleClickHandler(I,J,finalGrid);

                    }
                });

            }
        }


        return gridPane;
    }

    public BorderPane refreshBorder(Statistics stats){

        Button reset = new Button();
        reset.setText("Human");
        reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                Controller controller = new Controller();
                controller.resetClickHandler();

            }
        });

        Button robot = new Button();
        robot.setText("Robot");
        robot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                Controller controller = new Controller();
                controller.robotClickHandler();

            }
        });


        Label mode = new Label();
        Label white = new Label();
        Label winner = new Label();


        mode.setText(stats.getMode());

        white.setText("White: "+stats.getWhiteScore()+" Black: "+stats.getBlackScore()+" "+"Turn: "+stats.getTurn());
        winner.setText("Current Winner: "+stats.getCurrentWinner());

        borderPane.setLeft(reset);
        borderPane.setAlignment(reset,Pos.CENTER_LEFT);

        borderPane.setTop(white);
        borderPane.setAlignment(white,Pos.TOP_CENTER);

        borderPane.setBottom(mode);
        borderPane.setAlignment(mode, Pos.CENTER_RIGHT);

        borderPane.setCenter(winner);
        borderPane.setAlignment(winner,Pos.CENTER);

        borderPane.setRight(robot);
        borderPane.setAlignment(robot,Pos.CENTER_LEFT);

        return borderPane;

    }

    public void initializeStage(Stage primaryStage,Block[][] grid,Statistics stats){

        gridPane = refreshGrid(grid);
        borderPane = refreshBorder(stats);
        mainGridPane.add(gridPane,1,1);
        mainGridPane.add(borderPane,1,2);
        stackPane.getChildren().addAll(mainGridPane);
        Scene scene = new Scene(stackPane, 640, 700);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(scene);
        primaryStage.show();

    }










}





