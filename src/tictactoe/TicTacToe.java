/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author ACER
 */
public class TicTacToe {
public static int status(int[][] board)
{
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
    /*for(int r = 0; r < 3; r++)
    {
        for(int c = 0; c < 3; c++)
        {
            if(board[r][c] == 0)
                return 0;
        }
    }*/
    return 0;
}
/*public static int[][] miniMax(int[][] board, boolean MP, int depth)
{
    if (status(board) == 1 && MP == true)
        return board;
    int[][] storage = board;
    for(int r = 0; r < 3; r++)
    {
        for(int c = 0; c < 3; c++)
        {
            storage = board;
            if(board[r][c] == 0)
            {
                storage[r][c] = 1;
                if(depth%2 == 0)
                {
                    return miniMax(storage, MP, depth + 1);
                }
                else
                {
                    return miniMax(storage, !MP, depth + 1);
                }
            }
        }
    }
    return storage;
}*/
/*public static int[][] bestOppMove(int[][] board)
{
    int[][] storage = board;
    for(int r = 0; r < 3; r++)
    {
        for(int c = 0; c < 3; c++)
        {
            storage = board;
            if(board[r][c] == 0)
            {
                storage[r][c] = 2;
                if(status(bestMove(storage)) == 2)
                {
                return storage;
                }
            }
        }
    }
}*/
    /**
     * @param board
     * @param depth
     * @param maxPlayer
     * @return 
     */
public static int miniMax(Board board, int depth, boolean maxPlayer)
{
    if(board.status() == 1)
    {
        return 1;
    }
    if(board.status() == -1)
    {
        return -1;
    }
    if(board.status() == 0 && board.getCells() == 0)
    {
        return 0;
    }
    if(maxPlayer)
    {
        int value = -1;
        for(int r = 0; r < board.getRLength(); r++)
        {
            for(int c = 0; c < board.getCLength(); c++)
            {
                if(board.getSlot(r, c) == 0)
                {
                    value = Math.max(value, miniMax(board.setSlot(r, c, 1), depth - 1, false ));
                    return value;
                }
            }
        }
    }
    else
    {
        int value = 1;
        for(int r = 0; r < board.getRLength(); r++)
        {
            for(int c = 0; c < board.getCLength(); c++)
            {
                if(board.getSlot(r, c) == 0)
                {
                    value = Math.max(value, miniMax(board.setSlot(r, c, -1), depth - 1, true ));
                    return value;
                }
            }
        }
    }
    return -666;
}
public static int iOfBest(ArrayList<Integer> list)
{
    ArrayList<Integer> copy =  new ArrayList<>(list);
    Collections.sort(copy);
    //System.out.println("copy: " + copy);
    int best = copy.get(copy.size()-1);
//    for(int i = 0; i < copy.size() - 1; i++)
//    {
//        best = Math.max(copy.get(i), copy.get(i + 1));
//    }
    return list.indexOf(best);
}
public static int[][] realClone(int[][] k)
{
    int[][] res = new int[k.length][k[0].length];
    for(int r = 0; r < k.length; r++)
    {
        for(int c = 0; c < k[0].length; c++)
        {
            res[r][c] = k[r][c];
        }
    }
    return res;
}
    public static void main(String[] args) 
    {
        Board example = new Board();
        int counter = 0;
        //System.out.println("initial cellsLeft: " + cellsLeft(example));
        while(example.getCells() != 0)
        //while(counter < 2)
        {
//Prints the game
        example.toString();
        
        System.out.println("Row:");
        Scanner poop = new Scanner(System.in);
        int row = poop.nextInt() - 1;
        System.out.println("Collumn:");
        poop = new Scanner(System.in);
        int col = poop.nextInt() - 1;
        example[row][col] = -1;
        if (status(example) == 1){
            System.out.println("One wins");
            break;
        }
        if(status(example) == -1){
            System.out.println("Negative one wins");
            break;
        }
        //System.out.println("cellsLeft of example: " + cellsLeft(example));
        int[][] temp = realClone(example);
        //System.out.println("cellsLeft of temp: " + cellsLeft(temp));
        ArrayList<Integer> movesTrue = new ArrayList<>();
        ArrayList<Integer> movesFalse = new ArrayList<>();
        //boolean leaveLoop = false;
        for(int r = 0; r < example.length; r++)
        {
            for(int c = 0; c < example[0].length; c++)
            {
                if(temp[r][c] == 0)
                {
                    temp[r][c] = 1;
                    //int[][] storage = temp.clone();
                    //System.out.println("BEFORE miniMax " + cellsLeft(temp));
                    int left = cellsLeft(temp);
                    int valTrue = miniMax(temp, left, true);
                    int valFalse = miniMax(temp, left, false);
                    //System.out.println("AFTER miniMax " + cellsLeft(temp));                    
                    movesTrue.add(valTrue);
                    movesFalse.add(valFalse);
                    
                    temp = realClone(example);
                }
            }
        }
        System.out.println("If maximizing player = true: " + movesTrue);
        System.out.println("If maximizing player = false: " + movesFalse);
        //int iBest = iOfBest(movesTrue);
        for(int r = 0; r < example.length; r++)
        {
            for(int c = 0; c < example[0].length; c++)
            {
                //System.out.println(temp[r][c] == 0);
                //System.out.println("best move at index: " + iOfBest(moves) + "  counter:" + counter);
                
//                if(temp[r][c] != 0 && iBest == counter)
                
                if(temp[r][c] == 0 && iOfBest(movesTrue) == counter)
                {
                    example[r][c] = 1;
                    //System.out.println("Played at " + r + "  " + c);
                    counter = 1000;
                }
                if(temp[r][c] == 0)
                    counter++;
            }
        }
        counter = 0;
        if (status(example) == 1)
            System.out.println("One wins");
        if(status(example) == -1)
            System.out.println("Negative one wins");
    }
     for(int i = 0; i < 3; i ++)
        {
            for(int p = 0; p < 3; p++)
            {
                if(p == 0 || p == 1)
                {
                    System.out.print(" " + XO(example[i][p]) + " ");
                    System.out.print("|");
                }
                else
                    System.out.println(" " + XO(example[i][p]) + " ");
            }
            if(i < 2)
            {
                System.out.println("-----------");
            }
        }   
    }
}
