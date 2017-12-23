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
        Point end;

        if (target.getPosition().x > this.start.x) {
            // Rook on the right side
            rook = board.getTile(new Point(target.getPosition().x + 1, target.getPosition().y)).getPiece();
            end = new Point(target.getPosition().x - 1, target.getPosition().y);
        } else {
            // Rook on the left side
            rook = board.getTile(new Point(target.getPosition().x - 2, target.getPosition().y)).getPiece();
            end = new Point(target.getPosition().x + 1, target.getPosition().y);
        }

        Piece king = gameManager.getTile(start).getPiece();

        board.setTile(target.getPosition(), new Tile(king));
        board.setTile(rook.getPosition(), new Tile(rook.getPosition()));
        board.setTile(end, new Tile(rook));
    }
}
