package edu.andreasgut.labyrinth;

import java.util.Random;

public class Maze {
    public static void main(String[] args) {
        int numRows = 10; // Anzahl der Zeilen im Labyrinth
        int numCols = 20; // Anzahl der Spalten im Labyrinth

        boolean[][] maze = generateMaze(numRows, numCols);
        printMaze(maze);
    }

    public static boolean[][] generateMaze(int numRows, int numCols) {
        Random random = new Random();
        boolean[][] maze = new boolean[numRows][numCols];

        // Initialisiere das Labyrinth mit Wänden (false)
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                maze[row][col] = false;
            }
        }

        // Wähle einen zufälligen Startpunkt auf der linken Seite
        int startRow = random.nextInt(numRows);
        maze[startRow][0] = true;

        // Generiere das Labyrinth mit dem Prim-Algorithmus
        while (true) {
            boolean hasChanged = false;

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    // Überspringe ungerade Zeilen und Spalten, um ein gewundenes Labyrinth zu erzeugen
                    if (row % 2 == 1 || col % 2 == 1) {
                        continue;
                    }

                    // Überprüfe, ob das Feld eine Wand ist und mindestens ein Nachbarweg hat
                    if (!maze[row][col]) {
                        int wallNeighbors = 0;

                        if (row > 1 && maze[row - 2][col]) {
                            wallNeighbors++;
                        }

                        if (row < numRows - 2 && maze[row + 2][col]) {
                            wallNeighbors++;
                        }

                        if (col > 1 && maze[row][col - 2]) {
                            wallNeighbors++;
                        }

                        if (col < numCols - 2 && maze[row][col + 2]) {
                            wallNeighbors++;
                        }

                        if (wallNeighbors == 1) {
                            maze[row][col] = true;
                            hasChanged = true;
                        }
                    }
                }
            }

            if (!hasChanged) {
                break;
            }
        }

        return maze;
    }

    public static void printMaze(boolean[][] maze) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                System.out.print(maze[row][col] ? "O" : "#"); // Weg oder Wand
            }
            System.out.println();
        }
    }
}

