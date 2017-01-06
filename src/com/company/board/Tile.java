package com.company.board;

import com.company.piece.Piece;

import java.awt.*;

public class Tile {

    private Piece piece;

    public Tile() {
        this.piece = null;
    }

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }
    
    public Piece getPiece() {
        return this.piece;
    }

    public Point getPiecePosition() {
        return this.piece.getPosition();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "EMPTY";
        } else {
            return getPiece().toString();
        }
    }
}
