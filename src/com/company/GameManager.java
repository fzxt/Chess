package com.company;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class GameManager {
    private Player currentPlayer;

    private Player white;
    private Player black;

    private final Board board;

    private Piece selectedPiece;

    public GameManager() {
        this.white = new Player(Team.WHITE);
        this.black = new Player(Team.BLACK);
        this.currentPlayer = white;
        this.board = new Board(white, black);
        this.selectedPiece = null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void removePieceFromGame(Piece piece) {
        if (white.pieces.contains(piece)) {
            white.pieces.remove(piece);
        } else if (black.pieces.contains(piece)) {
            black.pieces.remove(piece);
        }

        piece.setPosition(new Point(-1, -1));
    }

    public void unhighlightBoard() {
        board.unhighlightBoard();
    }

    public void setTile(Point point, Tile tile) {
        board.setTile(point, tile);
    }

    public void nextTurn() {
        currentPlayer = currentPlayer == white ? black : white;
    }

    public Tile getTile(int x, int i) {
        return getBoard().getTile(x, i);
    }
}
