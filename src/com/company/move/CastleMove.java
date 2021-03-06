package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;
import com.company.piece.PieceType;

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
            rook = board.getTile(7, target.getPosition().y).getPiece();
            rookFinalPosition = new Point(target.getPosition().x - 1, target.getPosition().y);
        } else {
            // Rook on the left side
            rook = board.getTile(0, target.getPosition().y).getPiece();
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
        // Find the king, then move him to the start
        Piece king = board.getTile(end).getPiece();
        // Find the rook, then move him either to the left end or the right end
        Piece rook;

        Tile leftFinalPosition = board.getTile(new Point(end.x + 1, end.y));
        Tile rightFinalPosition = board.getTile(new Point(end.x - 1, end.y));

        if (!leftFinalPosition.isEmpty() && leftFinalPosition.getPiece().getType() == PieceType.ROOK) {
            rook = leftFinalPosition.getPiece();
            board.clearTile(leftFinalPosition.getPosition());
            rook.setPosition(new Point(0, end.y));
            board.getTile(0, end.y).setPiece(rook);
        } else if (!rightFinalPosition.isEmpty() && rightFinalPosition.getPiece().getType() == PieceType.ROOK) {
            rook = rightFinalPosition.getPiece();
            board.clearTile(rightFinalPosition.getPosition());
            rook.setPosition(new Point(7, end.y));
            board.getTile(7, end.y).setPiece(rook);
        }

        board.clearTile(end);
        king.setPosition(start);
        board.getTile(start).setPiece(king);
    }

    @Override
    public Move copy() {
        return new CastleMove(new Point(this.start), new Point(this.end));
    }
}
