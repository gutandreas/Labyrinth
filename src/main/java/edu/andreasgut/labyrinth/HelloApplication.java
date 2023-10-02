package edu.andreasgut.labyrinth;

import edu.andreasgut.labyrinth.controller.LabyrinthController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("labyrinth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Labyrinth!");
        stage.setScene(scene);
        stage.show();
        LabyrinthController controller = fxmlLoader.getController();
        Labyrinth labyrinth = new Labyrinth(30);
        controller.setLabyrinth(labyrinth);
        controller.addPositionToWay(new Position(0, 1));

    }

    public static void main(String[] args) {
        launch();







    }
}