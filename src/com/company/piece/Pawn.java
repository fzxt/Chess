package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
    
    public Pawn(Team team, int value, PieceType type, Point position){
        super(team, value, type, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        Point currPosition = super.getPosition();

        throw new UnsupportedOperationException("En passant not implemented"); //To change body of generated methods, choose Tools | Templates.
    }
}
