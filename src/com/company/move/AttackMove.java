package com.company.move;

import com.company.GameManager;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class AttackMove extends Move {
    public AttackMove(Point start, Point end) {
        super(start, end, MoveType.ATTACK);
    }

    @Override
    public void handleMove(GameManager gameManager, Tile target) {
        Piece attackedPiece = target.getPiece();
        super.handleMove(gameManager, target);
        gameManager.removePieceFromGame(attackedPiece);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.RED;
    }
}
