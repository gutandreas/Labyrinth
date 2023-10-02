package edu.andreasgut.labyrinth;

import java.util.Random;

public class Labyrinth {

    int dimension;
    int level;
    boolean[][] labyrinth;
    int startRow;
    int startCol;
    int endRow;
    int endCol;

    public Labyrinth(int dimension) {
        this.dimension = dimension;
        labyrinth = new boolean[dimension][dimension];
        generateLabyrinth(dimension);
        printMaze();

    }

    public void generateLabyrinth(int dimension){

        Random random = new Random();
        int tempRow = random.nextInt(dimension);
        int tempCol = 0;

        startRow = tempRow;
        startCol = tempCol;

        labyrinth[tempRow][tempCol] = true;
        getNextPositionRecursive(tempRow, tempCol);

    }

    public void getNextPositionRecursive(int row, int col){

        if (col == dimension-1){
            endRow = row;
            endCol = col;
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

            if (tempRow >= 0 && tempRow < dimension && tempCol >= 0 && tempCol < dimension && countNeighbours(tempRow, tempCol) == 1 ) {
                labyrinth[tempRow][tempCol] = true;
                neighbourFound = true;
                getNextPositionRecursive(tempRow, tempCol);
            }

        }

    }

    private int countNeighbours(int row, int col){
        int neighbours = 0;

        int[] i = {1, -1, 0, 0};
        int[] j = {0, 0, 1, -1};

        for (int counter = 0; counter < 4; counter++){
            if (row+i[counter] >= 0 && row+i[counter] < dimension && col+j[counter] >= 0 && col+j[counter] < dimension)
                if (labyrinth[row+i[counter]] [col+j[counter]]) {
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

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }
}
