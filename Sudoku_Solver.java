package Sudoku;
import java.util.Scanner;
/*
 * Author : Imtiaz Adar
 * Project : Sudoku Solver
 * Language : Java
 */
public class Sudoku_Solver {
    public static int r = 0, c = 0;
    public static void printBoard(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            if(i == 3 || i == 6)
                System.out.println();
            for(int j = 0; j < board.length; j++) {
                if(j == 2 || j == 5) {
                    System.out.print(board[i][j] + "  ");
                }
                else if(j < 8) {
                    System.out.print(board[i][j] + " ");
                }
                else System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static boolean isNextEmpty(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == 0) {
                    r = i;
                    c = j;
                    return true;
                }
            }
        }
        r = 0; c = 0;
        return false;
    }
    public static boolean uniqueInRow(int[][] board, int guessedNum, int row) {
        for(int j = 0; j < board.length; j++) {
            if(board[row][j] == guessedNum)
                return false;
        }
        return true;
    }
    public static boolean uniqueInColumn(int[][] board, int guessedNum, int col) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][col] == guessedNum)
                return false;
        }
        return true;
    }
    public static boolean uniqueInBlock(int[][] board, int guessedNum, int row, int col) {
        int row_block = (row / 3) * 3;
        int column_block = (col / 3) * 3;
        for(int i = row_block; i < row_block + 3; i++) {
            for(int j = column_block; j < column_block + 3; j++) {
                if(board[i][j] == guessedNum)
                    return false;
            }
        }
        return true;
    }
    public static boolean isMatched(int[][] board, int guessedNum, int row, int col) {
        return uniqueInRow(board, guessedNum, row) && uniqueInColumn(board, guessedNum, col)
                && uniqueInBlock(board, guessedNum, row, col);
    }
    public static boolean solveSudoku(int[][] board) {
        boolean is_empty = isNextEmpty(board);
        int row = r, col = c;
        if(!is_empty) {
            return true;
        }
        else {
            for(int guess = 1; guess < 10; guess++) {
                if(isMatched(board, guess, row, col)) {
                    board[row][col] = guess;
                    if(solveSudoku(board)) {
                        return true;
                    }
                    else {
                        board[row][col] = 0;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] board =
                {
                        {0, 0, 0,  0, 7, 2,  0, 0, 0},
                        {6, 0, 0,  0, 3, 0,  0, 0, 0},
                        {0, 2, 7,  5, 0, 9,  6, 1, 0},

                        {1, 0, 5,  0, 6, 0,  4, 2, 0},
                        {9, 0, 2,  0, 1, 5,  3, 0, 0},
                        {0, 0, 0,  9, 0, 0,  0, 6, 1},

                        {4, 0, 6,  1, 0, 0,  8, 3, 0},
                        {7, 0, 0,  0, 8, 0,  1, 9, 0},
                        {0, 1, 8,  0, 9, 6,  0, 4, 5}
                };
        System.out.println("# Before solved #");
        printBoard(board);
        boolean isDone = solveSudoku(board);
        if(isDone) {
            System.out.println("# After solved #");
            printBoard(board);
        }
        else System.out.println("# No Solutions #");
    }
}