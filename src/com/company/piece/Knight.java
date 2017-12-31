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
    public int[][] positionTable() {
        return new int[][] {
                { -50,-40,-30,-30,-30,-30,-40,-50 },
                { -40,-20,  0,  0,  0,  0,-20,-40 },
                { -30,  0, 10, 15, 15, 10,  0,-30 },
                { -30,  5, 15, 20, 20, 15,  5,-30 },
                { -30,  0, 15, 20, 20, 15,  0,-30 },
                { -30,  5, 10, 15, 15, 10,  5,-30 },
                { -40,-20,  0,  5,  5,  0,-20,-40 },
                { -50,-40,-30,-30,-30,-30,-40,-50 }
        };
    }

    /**
     * Unused for knight
     * @return
     */
    @Override
    public boolean[] positionThreats() {
        return new boolean[0];
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

    @Override
    public Piece copy() {
        return new Knight(this.getTeam(), new Point(this.getPosition()));
    }

    private Point generatePos(int x, int y) {
        Point currPos = getPosition();
        return new Point(currPos.x + x, currPos.y + y);
    }
}
