package edu.andreasgut.labyrinth.controller;

import edu.andreasgut.labyrinth.core.Labyrinth;
import edu.andreasgut.labyrinth.core.Position;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LabyrinthController implements Initializable {

    @FXML
    private GridPane labyrinthGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labyrinthGrid.setGridLinesVisible(false);
    }

    public void setLabyrinth(Labyrinth labyrinth){
        labyrinthGrid.setGridLinesVisible(false);
        boolean[][] labyrinthArray = labyrinth.getLabyrinth();
        int numRows = labyrinthArray[0].length;
        int numCols = labyrinthArray.length;
        Scene scene = labyrinthGrid.getScene();
        int rectangleHeight = (int) Math.ceil(scene.getHeight()/(numRows+2));
        int rectangleWidth = (int) Math.ceil(scene.getWidth()/(numCols+2));

        // Rand oben und unten
        for (int col = 0; col <= numCols; col++) {
            Rectangle rectangleTop = new Rectangle(rectangleHeight, rectangleWidth);
            rectangleTop.setFill(Color.BLACK);
            labyrinthGrid.add(rectangleTop, col, 0);
            Rectangle rectangleBottom = new Rectangle(rectangleHeight, rectangleWidth);
            rectangleBottom.setFill(Color.BLACK);
            labyrinthGrid.add(rectangleBottom, col, numRows+1);
        }

        // Rand links und rechts
        for (int row = 0; row <= numRows+1; row++) {
            Rectangle rectangleLeft = new Rectangle(rectangleHeight, rectangleWidth);
            rectangleLeft.setFill(Color.BLACK);
            labyrinthGrid.add(rectangleLeft, 0, row);
            Rectangle rectangleRight = new Rectangle(rectangleHeight, rectangleWidth);
            rectangleRight.setFill(Color.BLACK);
            labyrinthGrid.add(rectangleRight, numCols+1, row);
        }

        Rectangle startRectangle = new Rectangle(rectangleHeight, rectangleWidth);
        startRectangle.setFill(Color.LIGHTCORAL);
        labyrinthGrid.add(startRectangle,  labyrinth.getStartPosition().getColumn(), labyrinth.getStartPosition().getRow()+1);

        Rectangle goalRectangle = new Rectangle(rectangleHeight, rectangleWidth);
        goalRectangle.setFill(Color.LIGHTGREEN);
        labyrinthGrid.add(goalRectangle,  labyrinth.getGoalPosition().getColumn()+2, labyrinth.getGoalPosition().getRow()+1);





        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Rectangle rectangle = new Rectangle(rectangleHeight, rectangleWidth); // Größe der Rechtecke anpassen

                if (labyrinthArray[row][col]){
                    rectangle.setFill(Color.WHITE);
                }
                else {
                    rectangle.setFill(Color.BLACK);
                }
                labyrinthGrid.add(rectangle, col+1, row+1);
            }
        }
    }


    public void checkSolution(LinkedList<Position> solution){
        for (Position p : solution){
            colorPosition(p, Color.LIGHTBLUE);
        }
    }


    private void colorPosition(Position position, Color color){

        Rectangle r = (Rectangle) getChildFromGridPane(labyrinthGrid, position.getRow()+1, position.getColumn()+1 );
        if (r.getFill().equals(Color.WHITE)){
            r.setFill(color);
        }
        else {
            r.setFill(Color.RED);
        }
    }



    private Node getChildFromGridPane(GridPane gridPane, int rowIndex, int colIndex) {
        Node result = null;

        for (Node node : gridPane.getChildren()) {
            Integer row = GridPane.getRowIndex(node);
            Integer col = GridPane.getColumnIndex(node);

            if (row != null && col != null && row == rowIndex && col == colIndex) {
                result = node;
                break;
            }
        }

        return result;
    }


}
