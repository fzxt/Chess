package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import com.company.board.MoveType;
import com.company.board.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {

    public King(Team team, Point position) {
        super(team, 100, PieceType.KING, position);
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
                            moves.add(createMove(possiblePos));
                        } else {
                            if (!sameTeam(possibleTile.getPiece())) {
                                moves.add(createMove(possiblePos, MoveType.ATTACK));
                            }
                        }
                    }
                }
            }
        }
        System.err.println("Castling not yet implemented.");
        return moves;
    }

}
