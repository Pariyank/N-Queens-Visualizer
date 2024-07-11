import java.util.*;

public class Main {

    // Function to check if the cell in which queen is to be placed is safe or not.
    public static boolean isSafe(int row, int col, char[][] board) {
        // Checking horizontally if any other queen has been already placed in that row.
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }

        // Checking vertically if any other queen has been already placed in that column.
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Checking if queen has been already placed in the upper left diagonal
        int r = row;
        for (int c = col; c >= 0 && r >= 0; c--, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Checking if queen has been already placed in the upper right diagonal
        r = row;
        for (int c = col; c < board.length && r >= 0; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Checking if queen has been already placed in the lower left diagonal
        r = row;
        for (int c = col; c >= 0 && r < board.length; r++, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Checking if queen has been already placed in the lower right diagonal
        for (int c = col; c < board.length && r < board.length; c++, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // To save each arrangement present in board of characters,
    // we first convert it as board of strings and then save the list in allBoards
    public static void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>();
        
        // Traversing through the board of characters and saving each row as a string in newBoard
        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board[0].length; j++) {
                row.append(board[i][j]);
            }
            newBoard.add(row.toString());
        }
        
        // Adding the list "newBoard" to list of lists of string called "allBoards"
        allBoards.add(newBoard);
    }

    // Recursive function that explores possible moves to place a queen column by column
    public static void solve(char[][] board, List<List<String>> allBoards, int col) {
        // If col equals to board.length, all columns have been explored and function solve is ready with its solution.
        if (col == board.length) {
            // When we have one of the ways to put queens, we save it as a list called "board" in list of lists "allBoards"
            saveBoard(board, allBoards);
            return;
        }

        // Here we move column by column to place each queen in each column
        for (int row = 0; row < board.length; row++) {
            // If the queen is safe to be put in the cell, we put 'Q' in that cell to know that queen has been placed
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';

                // Print current board state (iteration)
                System.out.println("Iteration " + (col + 1) + ":");
                printBoard(board);

                // We again call the solve function by incrementing the column by 1.
                solve(board, allBoards, col + 1);

                // While backtracking, we put '.' in each cell showing that no queen has been placed there
                // This allows us to explore other solutions or moves with a clear board.
                board[row][col] = '.';
            }
        }
    }

    // Function to print the board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Main function to start solving the N-Queens problem
    public static void solution(int n) {
        // All boards will contain lists that are the possible solutions
        List<List<String>> allBoards = new ArrayList<>();

        // Each board will be of characters so that we can put 'Q' to refer to queen placement and '.' to refer that no queen is placed there
        char[][] board = new char[n][n];

        // Initialize the board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Calling the solve function to begin finding the possible arrangements
        solve(board, allBoards, 0);

        // Printing all solutions
        System.out.println("All solutions:");
        for (List<String> aboard : allBoards) {
            for (String row : aboard) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of queens (board size): ");
        int n = scanner.nextInt();
        scanner.close();

        // Call the solution method with user-provided size of the board
        solution(n);
    }
}
