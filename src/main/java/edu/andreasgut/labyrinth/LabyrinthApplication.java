package edu.andreasgut.labyrinth;

import edu.andreasgut.labyrinth.controller.LabyrinthController;
import edu.andreasgut.labyrinth.core.Labyrinth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LabyrinthApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LabyrinthApplication.class.getResource("labyrinth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.sizeToScene();
        stage.setTitle("Algorithmus zur Tiefensuche am Beispiel des Labyrinths");
        stage.setScene(scene);
        LabyrinthController controller = fxmlLoader.getController();
        Labyrinth labyrinth = new Labyrinth(50);
        controller.setLabyrinth(labyrinth);


        stage.show();


    }

    public static void main(String[] args) {
        launch();







    }
}