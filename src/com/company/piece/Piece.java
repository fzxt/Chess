package com.company.piece;

import com.company.Team;
import com.company.board.Move;

import java.util.ArrayList;

public abstract class Piece {

    private Team team;
    private int value;

    public Piece(Team team, int value) {
        this.team = team;
        this.value = value;
    }

    abstract ArrayList<Move> getAvailableMoves();
}
