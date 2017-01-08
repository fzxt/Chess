/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import com.company.board.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(Team team, Point position) {
        super(team, 3, PieceType.KNIGHT, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        Point[] possibleMoves = {
                generateMove(-1, -2),
                generateMove(1, -2),
                generateMove(-1, 2),
                generateMove(1, 2),
                generateMove(-2, -1),
                generateMove(2, -1),
                generateMove(-2, 1),
                generateMove(2, 1),
        };

        for (Point possibleMove: possibleMoves){
            if (board.validPosition(possibleMove)){
                Tile possibleTile = board.getTile(possibleMove);
                if (possibleTile.isEmpty()){
                    moves.add(createMove(possibleMove));
                }else if (possibleTile.getPiece().getTeam() != getTeam()){
                    moves.add(createMove(possibleMove));
                }
            }
        }
        return moves;
    }

    private Point generateMove(int x, int y) {
        Point currPos = getPosition();
        return new Point(currPos.x + x, currPos.y + y);
    }
}
