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
    public int[][] positionTable() {
        return new int[][] {
                { -30,-40,-40,-50,-50,-40,-40,-30 },
                { -30,-40,-40,-50,-50,-40,-40,-30 },
                { -30,-40,-40,-50,-50,-40,-40,-30 },
                { -30,-40,-40,-50,-50,-40,-40,-30 },
                { -20,-30,-30,-40,-40,-30,-30,-20 },
                { -10,-20,-20,-20,-20,-20,-20,-10 },
                {  20, 20,  0,  0,  0,  0, 20, 20 },
                {  20, 30, 10,  0,  0, 10, 30, 20 }
        };
    }

    @Override
    public boolean[] positionThreats() {
        return new boolean[] {true, true, true, true, true, true, true, true};
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

        // TODO: Fix castling.
        if (this.getPosition() == this.startPosition && getNumMoves() == 0) {
            int x = this.getPosition().x;
            int y = this.getPosition().y;

            for (int direction = -1; direction <= 1; direction += 2) {
                int left = x + direction;
                int right = x + (2 * direction);
                if (left < 0 || right < 0 || right > 7 || left > 7) {
                    break;
                }
                if (board.getTile(left, y).isEmpty() && board.getTile(right, y).isEmpty()) {
                    int potentialX = direction < 0 ? 0 : 7;
                    Tile potentialRook = board.getTile(new Point(potentialX, y));
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

    @Override
    public Piece copy() {
        return new King(this.getTeam(), new Point(this.getPosition()));
    }

    private Move createCastleMove(Point end) {
        return new CastleMove(this.startPosition, end);
    }

}
