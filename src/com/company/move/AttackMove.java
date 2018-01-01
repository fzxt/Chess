package com.company.move;

import com.company.GameManager;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class AttackMove extends Move {
    public AttackMove(Point start, Point end) {
        super(start, end, MoveType.ATTACK);
    }

    public AttackMove(Point start, Point end, MoveType type) {
        super(start, end, type);
    }

    @Override
    public void handleMove(Board board) {
        Piece attackedPiece = board.getTile(end).getPiece();
        GameManager.getInstance().removePieceFromGame(this, attackedPiece);
        super.handleMove(board);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.RED;
    }

    @Override
    public void undo(Board board) {
        Piece deadPiece = GameManager.getInstance().getDeadPieceFromMove(this);
        Piece moved = board.getTile(end).getPiece();

        deadPiece.setPosition(end);
        board.getTile(end).setPiece(deadPiece);
        board.getTile(start).setPiece(moved);
    }

    @Override
    public Move copy() {
        return new AttackMove(new Point(this.start), new Point(this.end), this.getType());
    }
}
