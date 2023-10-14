package edu.andreasgut.labyrinth.core;

import java.util.LinkedList;

public class StandardSolver {

    public static LinkedList<Position> solve(boolean[][] labyrinthArray, Position start, Position goal) {
        LinkedList<Position> path = new LinkedList<>();
        boolean[][] visited = new boolean[labyrinthArray.length][labyrinthArray[0].length];
        dfs(labyrinthArray, start, goal, visited, path);
        return path;
    }

    private static boolean dfs(boolean[][] labyrinthArray, Position current, Position goal,
                        boolean[][] visited, LinkedList<Position> path) {
        int row = current.getRow();
        int col = current.getColumn();

        if (current.equals(goal)) {
            path.add(current);
            return true;
        }

        if (row >= 0 && row < labyrinthArray.length && col >= 0 && col < labyrinthArray[0].length
                && labyrinthArray[row][col] && !visited[row][col]) {
            visited[row][col] = true;
            path.add(current);

            int[][] neighbors = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
            for (int[] neighbor : neighbors) {
                Position next = new Position(row + neighbor[0], col + neighbor[1]);
                if (dfs(labyrinthArray, next, goal, visited, path)) {
                    return true;
                }
            }

            path.removeLast();
        }

        return false;
    }
}
