package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Pawn;
import com.company.piece.Piece;

import java.awt.*;

public class PawnPromotionMove extends NormalMove {

    public PawnPromotionMove(Point start, Point end) {
        super(start, end, MoveType.PAWN_PROMOTION);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.YELLOW;
    }

    @Override
    public void undo(Board board) {
        Tile starting = board.getTile(start);
        Tile ending = board.getTile(end);

        // Get the promoted piece
        Piece promoted = ending.getPiece();

        // Clear the ending tile
        ending.setPiece(null);

        // Set starting to the pawn
        Pawn pawn = new Pawn(promoted.getTeam(), promoted.getPosition());
        starting.setPiece(pawn);
    }

    @Override
    public Move copy() {
        return new PawnPromotionMove(new Point(this.start), new Point(this.end));
    }
}
