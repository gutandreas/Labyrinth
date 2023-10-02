package edu.andreasgut.labyrinth;

import java.util.Random;

public class Labyrinth {

    int dimension;
    boolean[][] labyrinth;
    Position startPosition;
    Position goalPosition;

    public Labyrinth(int dimension) {
        this.dimension = dimension;
        labyrinth = new boolean[dimension][dimension];
        generateLabyrinth(dimension);
        generateWrongWays(dimension);
        printMaze();

    }

    public void generateLabyrinth(int dimension){

        Random random = new Random();
        int tempRow = random.nextInt(dimension);
        int tempCol = 0;

        startPosition = new Position(tempRow, tempCol);

        labyrinth[tempRow][tempCol] = true;
        getNextPositionRecursive(tempRow, tempCol);

    }

    public void getNextPositionRecursive(int row, int col){

        if (col == dimension-1){
            goalPosition = new Position(row, col);
            return;
        }

        int tempRow;
        int tempCol;

        boolean neighbourFound = false;

        while (!neighbourFound) {

            tempRow = row;
            tempCol = col;

            Random random = new Random();
            int direction = random.nextInt(4);

            if (direction == 3){
                if (!checkBackwardDirection(tempRow, tempCol)){
                    direction = 0;
                }
            }

            switch (direction) {
                case 0:
                    tempRow++;
                    break;
                case 1:
                    tempCol++;
                    break;
                case 2:
                    tempRow--;
                    break;
                case 3:
                    tempCol--;
                    break;
            }

            System.out.println("Getestete Position: " + tempRow + "/" + tempCol);
            printMaze();
            Position tempPosition = new Position(tempRow, tempCol);


            if (tempRow >= 0 && tempRow < dimension && tempCol >= 0 && tempCol < dimension && !labyrinth[tempRow][tempCol] && countNeighbours(tempPosition) == 1) {
                labyrinth[tempRow][tempCol] = true;
                neighbourFound = true;
                getNextPositionRecursive(tempRow, tempCol);
            }

        }

    }

    private void generateWrongWays(int dimension){
        Random random = new Random();

        for (int count = 0; count < Math.pow(dimension, 3); count++){
            Position position = new Position(random.nextInt(dimension), random.nextInt(dimension));

            if (countNeighbours(position) == 1){
                labyrinth[position.getRow()] [position.getColumn()] = true;
            }
        }

    }

    private boolean checkBackwardDirection(int row, int column){

        int tempColumn = column-1;

        return false;


    }

    private int countNeighbours(Position position){
        int neighbours = 0;

        int[] i = {1, -1, 0, 0};
        int[] j = {0, 0, 1, -1};

        for (int counter = 0; counter < 4; counter++){
            if (position.getRow()+i[counter] >= 0 && position.getRow()+i[counter] < dimension
                    && position.getColumn()+j[counter] >= 0 && position.getColumn()+j[counter] < dimension)
                if (labyrinth[position.getRow()+i[counter]] [position.getColumn()+j[counter]]) {
                    neighbours++;
                }
        }

        return neighbours;
    }

    public void printMaze(){
        for (int row = 0; row < dimension; row++){
            for (int col = 0; col < dimension; col++){
                if (labyrinth[row][col]){
                    System.out.print("X");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }



    public boolean[][] getLabyrinth() {
        return labyrinth;
    }


    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }
}
