import com.sun.org.glassfish.external.statistics.Stats;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * Created by pjoa09 on 9/16/17.
 */
public class View {

    private GridPane gridPane = new GridPane();
    private BorderPane borderPane = new BorderPane();
    private StackPane stackPane = new StackPane();


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
                        controller.clickHandler(I,J);
                        debug.printGrid(grid);

                        gridPane = refreshGrid();
                    }
                });

            }
        }



        return gridPane;
    }


}





