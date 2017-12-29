package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class CastleMove extends SpecialMove {
    public CastleMove(Point startPosition, Point end) {
        super(startPosition, end, MoveType.CASTLE);
    }

    @Override
    public void handleMove(Board board) {
        Tile target = board.getTile(end);
        Piece rook;
        Point rookFinalPosition;

        if (target.getPosition().x > this.start.x) {
            // Rook on the right side
            rook = board.getTile(target.getPosition().x + 1, target.getPosition().y).getPiece();
            rookFinalPosition = new Point(target.getPosition().x - 1, target.getPosition().y);
        } else {
            // Rook on the left side
            rook = board.getTile(target.getPosition().x - 2, target.getPosition().y).getPiece();
            rookFinalPosition = new Point(target.getPosition().x + 1, target.getPosition().y);
        }

        Piece king = board.getTile(start).getPiece();
        king.move(this);
        board.clearTile(rook.getPosition());
        rook.setPosition(rookFinalPosition);

        // Clear positions
        board.clearTile(start);
        board.clearTile(target.getPosition());
        board.clearTile(rookFinalPosition);

        board.getTile(target.getPosition()).setPiece(king);
        board.getTile(rookFinalPosition).setPiece(rook);
    }

    @Override
    public void undo(Board board) {

    }

    @Override
    public Move copy() {
        return new CastleMove(new Point(this.start), new Point(this.end));
    }
}
