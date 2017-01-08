package com.company.piece;

import com.company.Team;
import com.company.board.*;

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
            moves.add(createMove(singleMove));

            Point doubleMove = new Point(currentPos.x, currentPos.y + getNormalized(2));
            if (board.validPosition(doubleMove) && board.getTile(doubleMove).isEmpty() && getNumMoves() == 0) {
                moves.add(createMove(doubleMove, MoveType.NORMAL_DOUBLE));
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
                        moves.add(createMove(diagonalTile.getPosition(), MoveType.ATTACK));
                    }
                } else {
                    // En passant moves
                    if (currentPos.y == startPosition.y + getNormalized(3)) {
                        Tile sideTile = board.getTile(diagPos.x, diagPos.y + getNormalized(-1));
                        if (!sideTile.isEmpty()) {
                            Piece sidePiece = sideTile.getPiece();
                            if (sameType(sidePiece) && !sameTeam(sidePiece)) {
                                Move lastMove = MoveHistory.getLastMove(); //TODO: Change to use GameManager move history stack
                                Point lastPos = lastMove.getEnd();
                                Point sidePos = sideTile.getPosition();
                                if (lastMove.getType() == MoveType.NORMAL_DOUBLE && sidePos.equals(lastPos)) {
                                    moves.add(createMove(diagPos, MoveType.ENPASSANT));
                                }
                            }
                        }
                    }
                }
            }
        }

        // TODO: Implement pawn promotion
        System.err.println("Pawn promotion not yet implemented!");
        return moves;
    }

    private boolean sameType(Piece piece) {
        return this.getType() == piece.getType();
    }

    private int getNormalized(int value) {
        return value * direction;
    }

}
