package com.company.move;

import com.company.GameManager;
import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class EnpassantMove extends AttackMove {
    public EnpassantMove(Point start, Point end) {
        super(start, end, MoveType.ENPASSANT);
    }

    @Override
    public void handleMove(Board board) {
        int direction = board.getTile(start).getPiece().getTeam() == Team.WHITE ? 1 : -1;
        Piece attackedPiece = board.getTile(end.x, end.y+direction).getPiece();
        board.getTile(attackedPiece.getPosition()).setPiece(null);
        GameManager.getInstance().removePieceFromGame(this, attackedPiece);
        board.handleMove(this);
    }

    @Override
    public Move copy() {
        return new EnpassantMove(new Point(this.start), new Point(this.end));
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.YELLOW;
    }
}
