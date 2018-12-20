/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author ACER
 */
public class Board {
    private int[][] slots = new int[3][3];
    private int cellsLeft;

    public Board() {
        cellsLeft = 9;
    }

    public Board(Board p) {
        slots = realClone(p.slots);
        cellsLeft = p.cellsLeft;
    }

    public Board setSlot(int row, int col, int val) {
        Board r = copy(this);
        r.slots[row][col] = val;
        r.cellsLeft--;
        return r;
    }

    public int getCells() {
        return cellsLeft;
    }

    public int getRLength() {
        return slots.length;
    }

    public int getCLength() {
        return slots[0].length;
    }

    public Board copy(Board old) {
        Board n = new Board();
        n.slots = realClone(old.slots);
        n.cellsLeft = old.cellsLeft;
        return n;
    }

    public void changeTo(Board board) {
        slots = realClone(board.slots);
        cellsLeft = board.cellsLeft;
    }

    public int getSlot(int row, int col) {
        return slots[row][col];
    }

    public int[][] realClone(int[][] k) {
        int[][] res = new int[k.length][k[0].length];
        for(int r = 0; r < k.length; r++) {
            for(int c = 0; c < k[0].length; c++) {
                res[r][c] = k[r][c];
            }
        }
        return res;
    }

    public int status() {
        if (slots[0][0] == slots[0][1] && slots[0][0] == slots[0][2] && slots[0][0] != 0)
            return slots[0][0];
        if (slots[1][0] == slots[1][1] && slots[1][0] == slots[1][2] && slots[1][0] != 0)
            return slots[1][0];
        if (slots[2][0] == slots[2][1] && slots[2][0] == slots[2][2] && slots[2][0] != 0)
            return slots[2][0];
        if (slots[0][0] == slots[1][0] && slots[0][0] == slots[2][0] && slots[0][0] != 0)
            return slots[0][0];
        if (slots[0][1] == slots[1][1] && slots[0][1] == slots[2][1] && slots[0][1] != 0)
            return slots[0][1];
        if (slots[0][2] == slots[1][2] && slots[0][2] == slots[2][2] && slots[0][2] != 0)
            return slots[0][2];
        if (slots[0][0] == slots[1][1] && slots[0][0] == slots[2][2] && slots[0][0] != 0)
            return slots[0][0];
        if (slots[0][2] == slots[1][1] && slots[0][2] == slots[2][0] && slots[0][2] != 0)
            return slots[0][2];

        return 0;
    }

    public char slotChar(int row, int col) {
        int u = slots[row][col];
        if(u == -1) {
            return 'O';
        }
        if(u == 1) {
            return 'X';
        }
        else
            return ' ';
    }

    public void print() {
        for(int i = 0; i < 3; i ++) {
            for(int p = 0; p < 3; p++) {
                if(p == 0 || p == 1) {
                    System.out.print(" " + slotChar(i, p) + " ");
                    System.out.print("|");
                } else
                    System.out.println(" " + slotChar(i, p) + " ");
            }
            if(i < 2) {
                System.out.println("-----------");
            }
        }
    }
}
