package com.company.move;

import com.company.GameManager;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class CastleMove extends SpecialMove {
    public CastleMove(Point startPosition, Point end) {
        super(startPosition, end, MoveType.CASTLE);
    }

    @Override
    public void handleMove(GameManager gameManager, Tile target) {
        Board board = gameManager.getBoard();
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

        Piece king = gameManager.getTile(start).getPiece();
        king.move(this);
        gameManager.getBoard().setTile(rook.getPosition(), new Tile(rook.getPosition()));
        rook.setPosition(rookFinalPosition);

        // Clear positions
        gameManager.getBoard().setTile(start, new Tile(start));
        gameManager.getBoard().setTile(target.getPosition(), new Tile(target.getPosition()));
        gameManager.getBoard().setTile(rookFinalPosition, new Tile(rookFinalPosition));

        gameManager.getBoard().getTile(target.getPosition()).setPiece(king);
        gameManager.getBoard().getTile(rookFinalPosition).setPiece(rook);
    }

    @Override
    public void undo(GameManager gm) {
        System.err.println("Enpassant: Undo not implemented!");
    }
}
