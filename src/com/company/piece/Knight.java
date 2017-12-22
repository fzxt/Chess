package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.move.Move;
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
                generatePos(-1, -2),
                generatePos(1, -2),
                generatePos(-1, 2),
                generatePos(1, 2),
                generatePos(-2, -1),
                generatePos(2, -1),
                generatePos(-2, 1),
                generatePos(2, 1),
        };

        for (Point possibleMove: possibleMoves) {
            if (board.validPosition(possibleMove)) {
                Tile possibleTile = board.getTile(possibleMove);
                if (possibleTile.isEmpty()) {
                    moves.add(createNormalMove(possibleMove));
                } else if (!sameTeam(possibleTile.getPiece())) {
                    moves.add(createAttackMove(possibleMove));
                }
            }
        }
        return moves;
    }

    private Point generatePos(int x, int y) {
        Point currPos = getPosition();
        return new Point(currPos.x + x, currPos.y + y);
    }
}
