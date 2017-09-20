import com.sun.org.glassfish.external.statistics.Stats;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * Created by pjoa09 on 9/16/17.
 */
public class View {

    private GridPane gridPane = new GridPane();
    private BorderPane borderPane = new BorderPane();
    private MenuBar menuBar = new MenuBar();


    public GridPane refreshGrid() {
        gridPane.getChildren().clear();

        Image emptyTile = new Image(getClass().getResource("ReversiEmptyTile.png").toExternalForm());

        Image whiteTile = new Image("ReversiWhiteTile.png");
        Image blackTile = new Image("ReversiBlackTile.png");
        Controller controller = new Controller();
        Block[][] grid = controller.getGameBoard();
        Debug debug = new Debug();
        //debug.printGrid(grid);
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

                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        Controller controller1 = new Controller();
                        controller1.rectangleClickHandler(I,J);
                        gridPane = refreshGrid();
                        borderPane = refreshBorder();
                    }
                });

            }
        }


        return gridPane;
    }

    public BorderPane refreshBorder(){


        Controller controller = new Controller();
        Button reset = new Button();
        reset.setText("Human");
        reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Controller controller = new Controller();
                controller.resetClickHandler();
                gridPane = refreshGrid();
                borderPane= refreshBorder();
            }
        });

        Button robot = new Button();
        robot.setText("Robot");
        robot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Controller controller = new Controller();
                controller.robotClickHandler();
                gridPane = refreshGrid();
                borderPane= refreshBorder();
            }
        });


        Label mode = new Label();
        Label white = new Label();
        Label winner = new Label();

        mode.setText(controller.getGameMode());
        white.setText("White: "+controller.getCurrentWhiteScore()+" Black: "+controller.getCurrentBlackScore()+" "+"Turn: "+controller.getCurrentTurn());
        winner.setText("Current Winner: "+controller.getCurrentWinner());

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








}





