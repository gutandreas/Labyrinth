package edu.andreasgut.labyrinth;

import edu.andreasgut.labyrinth.controller.LabyrinthController;
import edu.andreasgut.labyrinth.core.Labyrinth;
import edu.andreasgut.labyrinth.core.Position;
import edu.andreasgut.labyrinth.core.Solver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        controller.checkSolution(Solver.solve(labyrinth.getLabyrinth(), labyrinth.getStartPosition(), labyrinth.getGoalPosition()));

        for (Position p : Solver.getSolution()){
            System.out.println(p);
        }


    }

    public static void main(String[] args) {
        launch();







    }
}