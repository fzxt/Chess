package com.company;

import com.company.piece.Piece;

import java.util.ArrayList;

/**
 * Class which represents a Player, holds a list of pieces
 */

public class Player {

    Team team;
    ArrayList<Piece> pieces;

    public Player(Team team) {
        this.pieces = new ArrayList<>();
        this.team = team;
    }

    void removePiece(Piece piece) {
        if (pieces.contains(piece)) {
            pieces.remove(piece);
        }
    }

    public Team getTeam() {
        return team;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    @Override
    public String toString() {
        return team.toString();
    }
}
