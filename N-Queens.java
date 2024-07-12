import java.util.*;

public class Main {

    public static boolean isSafe(int row, int col, char[][] board) {
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        int r = row;
        for (int c = col; c >= 0 && r >= 0; c--, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }


        r = row;
        for (int c = col; c < board.length && r >= 0; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        r = row;
        for (int c = col; c >= 0 && r < board.length; r++, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        for (int c = col; c < board.length && r < board.length; c++, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>();
        
        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board[0].length; j++) {
                row.append(board[i][j]);
            }
            newBoard.add(row.toString());
        }
        
        allBoards.add(newBoard);
    }

    public static void solve(char[][] board, List<List<String>> allBoards, int col) {
        if (col == board.length) {

            saveBoard(board, allBoards);
            return;
        }

        for (int row = 0; row < board.length; row++) {
         
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';

                System.out.println("Iteration " + (col + 1) + ":");
                printBoard(board);

                solve(board, allBoards, col + 1);

                board[row][col] = '.';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solution(int n) {
   
        List<List<String>> allBoards = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        solve(board, allBoards, 0);

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

        solution(n);
    }
}
