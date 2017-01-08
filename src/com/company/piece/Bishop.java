/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import com.company.board.MoveType;
import com.company.board.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Team team, Point position) {
        super(team, 3, PieceType.BISHOP, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        int[][] directionOffsets = {
                {1, 1}, // diagUpRight
                {-1, -1}, // diagDownLeft
                {1, -1}, // diagDownRight
                {-1, 1} // diagUpLeft
        };

        return getMovesInLine(board, directionOffsets);
    }
}
