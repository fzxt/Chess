package com.company.piece;

import com.company.Team;
import com.company.board.Move;

import java.util.ArrayList;

public abstract class Piece {

    private Team team;

    public Piece(Team team) {
        this.team = team;
    }

    abstract ArrayList<Move> getAvailableMoves();
}
