package com.company.piece;

import com.company.Team;
import com.company.board.Move;

import java.util.ArrayList;

public abstract class Piece {

    private Team team;
    private PieceType type;
    private int value;
    

    public Piece(Team team, int value, PieceType type) {
        this.team = team;
        this.value = value;
        this.type = type;
    }
    
    public Team getTeam(){
        return this.team;
    }

    public PieceType getType(){
        return this.type;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public abstract ArrayList<Move> getAvailableMoves();
}
