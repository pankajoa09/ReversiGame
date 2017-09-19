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
        Image emptyTile = new Image("ReversiEmptyTile.png");
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
                        controller.rectangleClickHandler(I,J);
                        gridPane = refreshGrid();
                        borderPane = refreshBorder();
                    }
                });

            }
        }


        return gridPane;
    }

    public BorderPane refreshBorder(){

        Button button = new Button();
        Controller controller = new Controller();
        button.setText("Reset");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                controller.resetClickHandler();
                gridPane = refreshGrid();
                borderPane= refreshBorder();
            }
        });


        Label turn = new Label();
        Label white = new Label();
        Label black = new Label();
        Label winner = new Label();

        turn.setText("Turn: "+controller.getCurrentTurn());
        white.setText("White: "+controller.getCurrentWhiteScore());
        black.setText("Black: "+controller.getCurrentBlackScore());
        winner.setText("Current Winner: "+controller.getCurrentWinner());

        borderPane.setBottom(button);
        borderPane.setTop(turn);
        borderPane.setLeft(white);
        borderPane.setRight(black);
        borderPane.setCenter(winner);
        borderPane.setAlignment(turn, Pos.CENTER);
        borderPane.setAlignment(white,Pos.CENTER_RIGHT);
        borderPane.setAlignment(black,Pos.CENTER_LEFT);
        borderPane.setAlignment(winner,Pos.BOTTOM_CENTER);
        borderPane.setAlignment(button,Pos.BOTTOM_CENTER);

        return borderPane;

    }








}





