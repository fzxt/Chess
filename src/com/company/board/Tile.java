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

    public Point getPosition() {
        return this.piece.getPosition();
    }
}
