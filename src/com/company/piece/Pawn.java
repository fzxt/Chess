package com.company.piece;

import com.company.Team;
import com.company.board.Move;
import java.util.ArrayList;

public class Pawn extends Piece {
    
    public Pawn(Team team, int value, PieceType type){
        super(team, value, type);
    }
    
    @Override
    public ArrayList<Move> getAvailableMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
