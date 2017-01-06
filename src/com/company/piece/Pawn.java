package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import com.company.board.MoveType;
import com.company.board.Tile;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Team team, Point position) {
        super(team, 1, PieceType.PAWN, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        Point currPos = super.getPosition();
        int direction = getTeam() == Team.BLACK ? 1 : -1;

        Point singleMove = new Point(currPos.x, currPos.y + direction);
        Point diagMoveLeft = new Point(currPos.x - 1, currPos.y + direction);
        Point diagMoveRight = new Point(currPos.x + 1, currPos.y + direction);

        if (board.getTile(singleMove).isEmpty()) {
            Point doubleMove = new Point(currPos.x, currPos.y + (2 * direction));
            moves.add(new Move(currPos, singleMove, MoveType.NORMAL));

            if (getNumMoves() == 0 && board.getTile(doubleMove).isEmpty()) {
                moves.add(new Move(currPos, doubleMove, MoveType.NORMAL));
            }
        }

        Tile diagLeftTile = board.getTile(diagMoveLeft);
        Tile diagRightTile = board.getTile(diagMoveRight);
        Tile[] diagonalTiles = { diagLeftTile, diagRightTile };

        for (Tile diagonalTile : diagonalTiles) {
            if (!diagonalTile.isEmpty() && diagonalTile.getPiece().getTeam() != getTeam()) {
                moves.add(new Move(currPos, diagonalTile.getPiecePosition(), MoveType.ATTACK));
            }
        }

        return moves;
    }
}
