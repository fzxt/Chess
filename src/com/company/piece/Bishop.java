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
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Move>[] directions = new ArrayList[]{
                generateMoves(board, 1, 1),
                generateMoves(board, -1, -1),
                generateMoves(board, 1, -1),
                generateMoves(board, -1, 1)
        };

        for(ArrayList<Move> directionMoves: directions){
            moves.addAll(directionMoves);
        }

        System.out.println(moves.size());
        return moves;
    }

    private ArrayList<Move> generateMoves(Board board, int x, int y) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            Point possiblePos = new Point(getPosition().x + (i * x),getPosition().y + (i * y));
            if (board.validPosition(possiblePos)){
                Tile possibleTile = board.getTile(possiblePos);
                if (possibleTile.isEmpty()){
                    moves.add(createMove(possiblePos));
                }else if(!sameTeam(possibleTile.getPiece())){
                    moves.add(createMove(possiblePos, MoveType.ATTACK));
                    break;
                }
            }else{
                break;
            }
        }
        return moves;
    }
}
