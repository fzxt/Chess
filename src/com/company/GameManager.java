package com.company;

import com.company.board.Board;

public class GameManager {
    private Player currentPlayer;

    private Player white;
    private Player black;

    private final Board board;

    public GameManager() {
        this.white = new Player(Team.WHITE);
        this.black = new Player(Team.BLACK);
        this.currentPlayer = white;
        this.board = new Board(white, black);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void nextTurn() {
        currentPlayer = currentPlayer == white ? black : white;
    }

}
