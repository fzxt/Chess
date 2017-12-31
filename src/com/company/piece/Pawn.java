package com.company.piece;

import com.company.Team;
import com.company.board.*;
import com.company.move.*;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
    Point startPosition;
    int direction;

    public Pawn(Team team, Point position) {
        super(team, 1, PieceType.PAWN, position);
        this.startPosition = new Point(position.x, position.y);
        this.direction = getTeam() == Team.BLACK ? 1 : -1;
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        Point currentPos = getPosition();
        Point singleMove = new Point(currentPos.x, currentPos.y + getNormalized(1));

        // Regular moves
        if (board.validPosition(singleMove) && board.getTile(singleMove).isEmpty()) {
            moves.add(createNormalMove(singleMove));

            Point doubleMove = new Point(currentPos.x, currentPos.y + getNormalized(2));
            if (board.validPosition(doubleMove) && board.getTile(doubleMove).isEmpty() && getNumMoves() == 0) {
                moves.add(createNormalMove(doubleMove, MoveType.NORMAL_DOUBLE));
            }
        }

        Point[] diagPositions = {
                new Point(currentPos.x - 1, currentPos.y + getNormalized(1)),
                new Point(currentPos.x + 1, currentPos.y + getNormalized(1))
        };

        for (Point diagPos : diagPositions) {
            if (board.validPosition(diagPos)) {
                // Attack moves
                Tile diagonalTile = board.getTile(diagPos);
                if (!diagonalTile.isEmpty()) {
                    if (!sameTeam(diagonalTile.getPiece())) {
                        moves.add(createAttackMove(diagonalTile.getPosition()));
                    }
                } else {
                    // En passant moves
                    if (currentPos.y == startPosition.y + getNormalized(3)) {
                        Tile sideTile = board.getTile(diagPos.x, diagPos.y + getNormalized(-1));
                        if (!sideTile.isEmpty()) {
                            Piece sidePiece = sideTile.getPiece();
                            if (sameType(sidePiece) && !sameTeam(sidePiece)) {
                                Move lastMove = MoveHistory.getInstance().popLastMove();
                                Point lastPos = lastMove.getEnd();
                                Point sidePos = sideTile.getPosition();
                                if (lastMove.getType() == MoveType.NORMAL_DOUBLE && sidePos.equals(lastPos)) {
                                    moves.add(createEnpassantMove(diagPos));
                                }
                            }
                        }
                    }
                }
            }
        }


        for (int i = 0 ; i < moves.size(); i++) {
            Move move = moves.get(i);
            if (move.getEnd().y == 7 || move.getEnd().y == 0) {
                moves.set(i, new PawnPromotionMove(currentPos, move.getEnd()));
            }
        }


        return moves;
    }

    @Override
    public Piece copy() {
        return new Pawn(this.getTeam(), new Point(this.getPosition()));
    }

    public boolean promotePawn() {
        return getTeam() == Team.WHITE && getPosition().y == 0 || getTeam() == Team.BLACK && getPosition().y == 7;
    }

    private Move createEnpassantMove(Point target) {
        return new EnpassantMove(this.startPosition, target.getLocation());
    }

    @Override
    public int[][] positionTable() {
        return new int[][] {
                {  0,  0,  0,  0,  0,  0,  0,  0 },
                { 50, 50, 50, 50, 50, 50, 50, 50 },
                { 10, 10, 20, 30, 30, 20, 10, 10 },
                {  5,  5, 10, 25, 25, 10,  5,  5 },
                {  0,  0,  0, 20, 20,  0,  0,  0 },
                {  5, -5,-10,  0,  0,-10, -5,  5 },
                {  5, 10, 10,-20,-20, 10, 10,  5 },
                {  0,  0,  0,  0,  0,  0,  0,  0 }
        };
    }

    @Override
    public boolean[] positionThreats() {
        boolean diag = getTeam() == Team.WHITE;
        return new boolean[] {!diag, false, !diag, false, false, diag, false, diag};
    }

    private boolean sameType(Piece piece) {
        return this.getType() == piece.getType();
    }

    private int getNormalized(int value) {
        return value * direction;
    }

}
