package mazeopgave;
import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
    
    // Maze representation
    static char[][] maze = {
        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
        {'*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*'},
        {'*', ' ', '*', ' ', '*', ' ', '*', '*', '*', ' ', '*', '*'},
        {'*', ' ', '*', 'S', '*', ' ', '*', ' ', '*', ' ', ' ', '*'},
        {'*', ' ', '*', '*', '*', ' ', '*', ' ', '*', '*', ' ', '*'},
        {'*', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', '*', ' ', '*'},
        {'*', '*', '*', ' ', '*', '*', '*', ' ', '*', '*', ' ', '*'},
        {'*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*'},
        {'*', ' ', '*', '*', '*', ' ', '*', ' ', '*', '*', 'G', '*'},
        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}
    };

    static int startRow = 3, startCol = 3; // 'S' position
    static int goalRow = 8, goalCol = 10; // 'G' position
    static List<String> path = new ArrayList<>();

    // Depth-First Search (DFS)
    public static boolean findPath(int row, int col) {
        // Out of bounds check
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) return false;
        
        // Stop if we reach goal
        if (row == goalRow && col == goalCol) {
            path.add("(" + row + "," + col + ")");
            return true;
        }
        
        // If it's a wall or already visited, return false
        if (maze[row][col] == '*' || maze[row][col] == '+') return false;

        // Mark as visited
        maze[row][col] = '+';
        path.add("(" + row + "," + col + ")");

        // Move in all 4 directions (right, down, left, up)
        if (findPath(row, col + 1) || 
            findPath(row + 1, col) || 
            findPath(row, col - 1) || 
            findPath(row - 1, col)) {
            return true;
        }

        // Backtrack if no path found
        path.remove(path.size() - 1);
        maze[row][col] = ' ';
        return false;
    }

    public static void printMaze() {
        for (char[] row : maze) {
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        System.out.println("Initial Maze:");
        printMaze();

        if (findPath(startRow, startCol)) {
            System.out.println("\nPath found: " + path);
        } else {
            System.out.println("\nNo path found.");
        }

        System.out.println("\nFinal Maze:");
        printMaze();
    }
}
