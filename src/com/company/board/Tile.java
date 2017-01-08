package com.company.board;

import com.company.Team;
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

    @Override
    public String toString() {
        String team = " ";
        if (!isEmpty()){
            team = this.piece.getTeam() == Team.BLACK ? "b" : "w";
        }
        return this.piece == null ? team + "NULL" : team + this.piece.getType().toString();
    }
}
