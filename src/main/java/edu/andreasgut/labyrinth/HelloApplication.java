package edu.andreasgut.labyrinth;

import edu.andreasgut.labyrinth.controller.LabyrinthController;
import edu.andreasgut.labyrinth.core.Labyrinth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("labyrinth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.sizeToScene();
        stage.setTitle("Labyrinth!");
        stage.setScene(scene);
        LabyrinthController controller = fxmlLoader.getController();
        Labyrinth labyrinth = new Labyrinth(50);
        controller.setLabyrinth(labyrinth);
        //controller.checkSolution(StandardSolver.solve(labyrinth.getLabyrinth(), labyrinth.getStartPosition(), labyrinth.getGoalPosition()));


        stage.show();


    }

    public static void main(String[] args) {
        launch();







    }
}