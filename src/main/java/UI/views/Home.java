package UI.views;

import UI.controller.PlaceController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Home extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
//        stage.setTitle("Lịch sử Việt Nam");
        stage.setScene(scene);
        //set stage borderless
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}