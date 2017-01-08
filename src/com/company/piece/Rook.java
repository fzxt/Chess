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
        ArrayList<Move> moves = getMovesInLine(board, directionOffsets);
        return moves;
    }
    
}
