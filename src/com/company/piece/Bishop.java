package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.move.Move;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Team team, Point position) {
        super(team, 30, PieceType.BISHOP, position);
    }

    @Override
    public int[][] positionTable() {
        return new int[][] {
                { -20,-10,-10,-10,-10,-10,-10,-20 },
                { -10,  0,  0,  0,  0,  0,  0,-10 },
                { -10,  0,  5, 10, 10,  5,  0,-10 },
                { -10,  5,  5, 10, 10,  5,  5,-10 },
                { -10,  0, 10, 10, 10, 10,  0,-10 },
                { -10, 10, 10, 10, 10, 10, 10,-10 },
                { -10,  5,  0,  0,  0,  0,  5,-10 },
                { -20,-10,-10,-10,-10,-10,-10,-20 }
        };
    }

    @Override
    public boolean[] positionThreats() {
        return new boolean[]{ true, false, true, false, false, true, false, true };
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        int[][] directionOffsets = {
                {1, 1}, // Upper right
                {-1, -1}, // Bottom left
                {1, -1}, // Bottom right
                {-1, 1} // Upper left
        };

        return getMovesInLine(board, directionOffsets);
    }

    @Override
    public Piece copy() {
        return new Bishop(this.getTeam(), new Point(this.getPosition()));
    }
}
