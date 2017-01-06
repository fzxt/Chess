package com.company.board;

import com.company.piece.Piece;

public class Tile {

    private Piece piece;

    public Tile() {
        this.piece = null;
    }

    public Tile(Piece piece) {
        this.piece = piece;
    }

    boolean isEmpty() {
        return this.piece == null;
    }

}
