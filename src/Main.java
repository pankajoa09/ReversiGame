/**
 * Created by pjoa09 on 9/11/17.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
        StackPane stackPane = new StackPane();

        GridPane mainGridPane = new GridPane();

        BorderPane borderPane = view.refreshBorder();
        GridPane gridPane = view.refreshGrid();
        mainGridPane.add(gridPane,1,1);
        mainGridPane.add(borderPane,1,2);
        stackPane.getChildren().addAll(mainGridPane);

        Scene scene = new Scene(stackPane, 640, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

    }








    }





