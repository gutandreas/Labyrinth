package edu.andreasgut.labyrinth.core;

import java.util.LinkedList;

public class StandardSolver {

    static LinkedList<Position> solution;

    public static LinkedList<Position> solve(boolean[][] labyrinthArray, Position start, Position goal) {
        LinkedList<Position> path = new LinkedList<>();
        boolean[][] visited = new boolean[labyrinthArray.length][labyrinthArray[0].length];

        if (dfs(labyrinthArray, start, goal, visited, path)) {
            solution = path;
            return path;
        } else {
            return null; // Keine Lösung gefunden
        }
    }

    private static boolean dfs(boolean[][] labyrinthArray, Position current, Position goal,
                        boolean[][] visited, LinkedList<Position> path) {
        int row = current.getRow();
        int col = current.getColumn();

        // Basisfall: Wenn der aktuelle Punkt das Ziel ist
        if (current.equals(goal)) {
            path.add(current);
            return true;
        }

        // Überprüfe, ob der aktuelle Punkt im Labyrinth liegt und nicht besucht wurde
        if (row >= 0 && row < labyrinthArray.length && col >= 0 && col < labyrinthArray[0].length
                && labyrinthArray[row][col] && !visited[row][col]) {
            visited[row][col] = true;
            path.add(current);

            // Rekursiver Aufruf für Nachbarpunkte (oben, unten, links, rechts)
            int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] neighbor : neighbors) {
                Position next = new Position(row + neighbor[0], col + neighbor[1]);
                if (dfs(labyrinthArray, next, goal, visited, path)) {
                    return true; // Wenn ein Weg gefunden wurde, beende die Suche
                }
            }

            // Wenn kein Weg gefunden wurde, entferne den aktuellen Punkt aus dem Pfad
            path.removeLast();
        }

        return false;
    }

    public static LinkedList<Position> getSolution() {
        return solution;
    }
}
