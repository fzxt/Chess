package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;

import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(Team team, Point position){
        super(team, 5, PieceType.ROOK, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        int[][] directionOffsets = {
                {0, 1}, // Up
                {0, -1}, // Down
                {1, 0}, // Left
                {-1, 0} // Right
        };

        return getMovesInLine(board, directionOffsets);
    }
    
}
