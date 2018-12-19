643wa/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author ACER
 */
public class Decision {
    private int best_state_value;
    private int[][] board = new int[3][3];
    public Decision(int v, int[][] b)
    {
        best_state_value = v;
        board = b;
    }
    public int getBSV()
    {
        return best_state_value;
    }
    public int[][] getBoard()
    {
        return board;
    }
}
