package com.example.rosan.tictactoe;

import android.widget.Toast;

import java.io.Serializable;

/* Created by rosan on 12-2-2018. */

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private Tile[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    public Integer movesPlayed;
    private Boolean gameOver;

    private GameState status;

    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = Tile.BLANK;

        playerOneTurn = true;
        gameOver = false;

        status = GameState.IN_PROGRESS;
        movesPlayed = 0;
    }

    public Tile draw(int row, int column) {
        // Retrieve the current value of the board at position (row, column)
        Tile tile = board[row][column];

        // If that place is still blank,
        if (tile == Tile.BLANK) {
            if (playerOneTurn){
                // Give turn to other player, change board and return value of Tile
                playerOneTurn = false;
                board[row][column] = Tile.CROSS;
                movesPlayed += 1;
                return Tile.CROSS;
            }
            else {
                // Give turn to other player, change board and return value of Tile
                playerOneTurn = true;
                board[row][column] = Tile.CIRCLE;
                movesPlayed += 1;
                return Tile.CIRCLE;
            }
        }
        else {
            return Tile.INVALID;
        }
    }

    public GameState gameState() {
        // 1 Diagonal left top and downright (CROSS && CIRCLE)
        if (board[0][0] == Tile.CROSS && board[1][1] == Tile.CROSS && board[2][2] == Tile.CROSS) {
            gameOver = true;
            return GameState.PLAYER_ONE;
        }
        if (board[0][0] == Tile.CIRCLE && board[1][1] == Tile.CIRCLE && board[2][2] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 2 Diagonal downright and left top CROSS && CIRCLE)
        if (board[0][2] == Tile.CROSS && board[1][1] == Tile.CROSS && board[2][0] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[0][2] == Tile.CIRCLE && board[1][1] == Tile.CIRCLE && board[2][0] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 3 first row
        if (board[0][0] == Tile.CROSS && board[0][1] == Tile.CROSS && board[0][2] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[0][0] == Tile.CIRCLE && board[0][1] == Tile.CIRCLE && board[0][2] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 4 second row
        if (board[1][0] == Tile.CROSS && board[1][1] == Tile.CROSS && board[1][2] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[1][0] == Tile.CIRCLE && board[1][1] == Tile.CIRCLE && board[1][2] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 5 third row
        if (board[2][0] == Tile.CROSS && board[2][1] == Tile.CROSS && board[2][2] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[2][0] == Tile.CIRCLE && board[2][1] == Tile.CIRCLE && board[2][2] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 6 first column
        if (board[0][0] == Tile.CROSS && board[1][0] == Tile.CROSS && board[2][0] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[0][0] == Tile.CIRCLE && board[1][0] == Tile.CIRCLE && board[2][0] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 7 second column
        if (board[0][1] == Tile.CROSS && board[1][1] == Tile.CROSS && board[2][1] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[0][1] == Tile.CIRCLE && board[1][1] == Tile.CIRCLE && board[2][1] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        // 8 third column
        if (board[0][2] == Tile.CROSS && board[1][2] == Tile.CROSS && board[2][2] == Tile.CROSS) {
            return GameState.PLAYER_ONE;
        }
        if (board[0][2] == Tile.CIRCLE && board[1][2] == Tile.CIRCLE && board[2][2] == Tile.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        if (board[0][0] != Tile.BLANK && board[0][1] != Tile.BLANK && board[0][2] != Tile.BLANK &&
                board[1][0] != Tile.BLANK && board[1][1] != Tile.BLANK && board[1][2] != Tile.BLANK &&
                board[2][0] != Tile.BLANK && board[2][1] != Tile.BLANK && board[2][2] != Tile.BLANK ){
            return GameState.DRAW;
        } else {
            return GameState.IN_PROGRESS;
        }
    }

    public int getMoves(){
        return movesPlayed;
    }
}
