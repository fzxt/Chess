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
    
}