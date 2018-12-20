package tictactoe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author ACER
 */
public class TicTacToe {
    Board currentBoard;

    TicTacToe() {}

    private int status(int[][] board) {
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != 0)
            return board[0][0];
        if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != 0)
            return board[1][0];
        if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != 0)
            return board[2][0];
        if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != 0)
            return board[0][0];
        if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != 0)
            return board[0][1];
        if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != 0)
            return board[0][2];
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 0)
            return board[0][0];
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != 0)
            return board[0][2];
        return 0;
    }
    /**
     * @param board
     * @param depth
     * @param maxPlayer
     * @return
     */
    private int miniMax(Board board, int depth, boolean maxPlayer) {
        if(board.status() == 1) {
            return 1;
        } else if(board.status() == -1) {
            return -1;
        } else if (board.status() == 0 && board.getCells() == 0){
            return 0;
        }

        if(maxPlayer) {
            int value = -1;
            for(int r = 0; r < board.getRLength(); r++) {
                for(int c = 0; c < board.getCLength(); c++) {
                    if(board.getSlot(r, c) == 0) {
                        System.out.println("maxPlayer: " + maxPlayer + "        depth: " + depth);
                        value = Math.max(value, miniMax(board.setSlot(r, c, 1), depth - 1, false ));
                        return value;
                    }
                }
            }

        } else {
            int value = 1;
            for(int r = 0; r < board.getRLength(); r++) {
                for(int c = 0; c < board.getCLength(); c++) {
                    if(board.getSlot(r, c) == 0) {
                        //System.out.println("maxPlayer: " + maxPlayer + "        depth: " + depth);
                        value = Math.min(value, miniMax(board.setSlot(r, c, -1), depth - 1, true ));
                        return value;
                    }
                }
            }
        }
        return -666;
    }

    private int iOfBest(ArrayList<Integer> list) {
        if (list.size() == 0)
            return -1;
        int maxIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(maxIndex))
                maxIndex = i;
        }
        return maxIndex;
    }

    private void getUserTurn() {
        //Prints the game
        currentBoard.print();

        System.out.println("Row:");
        Scanner poop = new Scanner(System.in);
        int row = poop.nextInt() - 1;

        System.out.println("Collumn:");
        poop = new Scanner(System.in);
        int col = poop.nextInt() - 1;

        currentBoard = currentBoard.setSlot(row, col, -1);
    }

    private void getComputerTurn() {
        //System.out.println("cellsLeft of currentBoard: " + cellsLeft(currentBoard));
        Board temp = new Board(currentBoard);
        System.out.println("after constructor: " + currentBoard.getCells());

        int maxValue = -666;
        Pair<Integer, Integer> best_coordinates = new Pair<Integer, Integer>();
        boolean valid_coordinates = false;

        for(int r = 0; r < currentBoard.getRLength(); r++) {
            for(int c = 0; c < currentBoard.getCLength(); c++) {
                if(temp.getSlot(r, c) == 0) {
                    int value = miniMax(currentBoard.setSlot(r, c, 1), currentBoard.getCells() - 1, true);
                    if (value > maxValue) {
                        valid_coordinates = true;
                        best_coordinates.first = r;
                        best_coordinates.second = c;
                    }
                }
            }
        }

        if (valid_coordinates) {
            currentBoard = currentBoard.setSlot(best_coordinates.first, best_coordinates.second, 1);
        } else {
            System.out.println("ERROR: No best move found, no moves available.");
        }
    }

    private boolean checkEndOfGame() {
        if (currentBoard.status() == 1) {
            System.out.println("One wins");
            return true;
        } else if (currentBoard.status() == -1) {
            System.out.println("Negative one wins");
            return true;
        } else if (currentBoard.getCells() == 0) {
            System.out.println("Game resulted in tie");
            return true;
        }
        return false;
    }

    public void play() {
        currentBoard = new Board();
        int counter = 0;
        boolean keepPlaying = true;
        while(keepPlaying) {
            getUserTurn();
            keepPlaying = !checkEndOfGame();

            getComputerTurn();
            keepPlaying = !checkEndOfGame();
        }
        currentBoard.print();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
