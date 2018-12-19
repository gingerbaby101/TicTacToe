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
    private int[][] slots;
    private int cellsLeft;
    public Board()
    {
        cellsLeft = 9;
    }
    public void setSlot(int row, int col, int val)
    {
        slots[row][col] = val;
    }
    public int getSlot(int row, int col)
    {
        return slots[row][col];
    }
    public int status()
    {
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
    public char slotChar(int row, int col)
    {
        int u = slots[row][col];
        if(u == -1)
        {
            return 'O';
        }
        if(u == 1)
        {
            return 'X';
        }
        else 
            return ' ';
    }
}
