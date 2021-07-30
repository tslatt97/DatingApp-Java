package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * Hovedfilen til JavaFX programmet, her kobles programmet opp til Scene Builder dokumentet (FXML) og setter også navn og størrelse på vinduet.
 *
 * @author Eksamensgruppe11
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        primaryStage.setTitle("Dating App");
        primaryStage.setScene(new Scene(root,910,600));
        primaryStage.show();


    }

    public static void main(String[] args) {

        launch(args);
    }
}
