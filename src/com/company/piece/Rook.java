package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.move.Move;

import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Team team, Point position){
        super(team, 5, PieceType.ROOK, position);
    }

    @Override
    public int[][] positionTable() {
        return new int[][] {
            {  0,  0,  0,  0,  0,  0,  0,  0 },
            {  5, 10, 10, 10, 10, 10, 10,  5 },
            { -5,  0,  0,  0,  0,  0,  0, -5 },
            { -5,  0,  0,  0,  0,  0,  0, -5 },
            { -5,  0,  0,  0,  0,  0,  0, -5 },
            { -5,  0,  0,  0,  0,  0,  0, -5 },
            { -5,  0,  0,  0,  0,  0,  0, -5 },
            {  0,  0,  0,  5,  5,  0,  0,  0 }
        };
    }

    @Override
    public boolean[] positionThreats() {
        return new boolean[]{false, true, false, true, true, false, true, false};
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

    @Override
    public Piece copy() {
        return new Rook(this.getTeam(), new Point(this.getPosition()));
    }

}
