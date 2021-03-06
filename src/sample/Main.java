package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.setTitle("Monster Gym");
        Image icon = new Image("monster gym.jpeg");
        loginStage.getIcons().add(icon);
        loginStage.setResizable(false);
        loginStage.centerOnScreen();
        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
