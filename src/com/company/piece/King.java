package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.move.CastleMove;
import com.company.move.Move;
import com.company.board.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {
    Point startPosition;
    public King(Team team, Point position) {
        super(team, 100, PieceType.KING, position);
        this.startPosition = position;
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Point possiblePos = new Point(getPosition().x + i, getPosition().y + j);
                if (!getPosition().equals(possiblePos)) {
                    if (board.validPosition(possiblePos)) {
                        Tile possibleTile = board.getTile(possiblePos);
                        if (possibleTile.isEmpty()) {
                            moves.add(createNormalMove(possiblePos));
                        } else {
                            if (!sameTeam(possibleTile.getPiece())) {
                                moves.add(createAttackMove(possiblePos));
                            }
                        }
                    }
                }
            }
        }

        // Castling
        // You can only castle at the start
        if (this.getPosition() == this.startPosition && getNumMoves() == 0) {
            int x = this.getPosition().x;
            int y = this.getPosition().y;

            for (int direction = -1; direction <= 1; direction += 2) {
                if (board.getTile(x + direction, y).isEmpty() && board.getTile(x + (2 * direction), y).isEmpty()) {
                    int potentialX = direction < 0 ? x - 4 : x + 3;
                    Tile potentialRook = board.getTile(potentialX, y);
                    if (!potentialRook.isEmpty() && potentialRook.getPiece().getType() == PieceType.ROOK) {
                        Piece rook = potentialRook.getPiece();
                        if (rook.getNumMoves() == 0) {
                            moves.add(createCastleMove(new Point(x + (2 * direction), y)));
                        }
                    }
                }
            }
        }

        return moves;
    }

    private Move createCastleMove(Point end) {
        return new CastleMove(this.startPosition, end);
    }

}
