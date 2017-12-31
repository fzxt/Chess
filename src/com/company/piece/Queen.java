package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.move.Move;
import java.awt.Point;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Team team, Point position){
        super(team, 9, PieceType.QUEEN, position);
    }

    @Override
    public int[][] positionTable() {
        return new int[][] {
                { -20,-10,-10, -5, -5,-10,-10,-20 },
                { -10,  0,  0,  0,  0,  0,  0,-10 },
                { -10,  0,  5,  5,  5,  5,  0,-10 },
                {  -5,  0,  5,  5,  5,  5,  0, -5 },
                {   0,  0,  5,  5,  5,  5,  0, -5 },
                { -10,  5,  5,  5,  5,  5,  0,-10 },
                { -10,  0,  5,  0,  0,  0,  0,-10 },
                { -20,-10,-10, -5, -5,-10,-10,-20 }
        };
    }

    @Override
    public boolean[] positionThreats() {
        return new boolean[] {true, true, true, true, true, true, true, true};
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        int[][] directionOffsets = {
                {0, 1}, // Up
                {0, -1}, // Down
                {1, 0}, // Left
                {-1, 0}, // Right
                {1, 1}, // diagUpRight
                {-1, -1}, // diagDownLeft
                {1, -1}, // diagDownRight
                {-1, 1} // diagUpLeft
        };

        return getMovesInLine(board, directionOffsets);
    }

    @Override
    public Piece copy() {
        return new Queen(this.getTeam(), new Point(this.getPosition()));
    }

}