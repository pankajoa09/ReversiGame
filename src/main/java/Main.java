/**
 * Created by pjoa09 on 9/11/17.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;





public class Main extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Controller controller = new Controller();

        controller.startApplication(primaryStage);

    }










    }





