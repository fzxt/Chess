package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class NormalMove extends Move {

    public NormalMove(Point start, Point end) {
        super(start, end, MoveType.NORMAL);
    }

    public NormalMove(Point start, Point end, MoveType type) {
        super(start, end, type);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.BLUE;
    }

    @Override
    public void undo(Board board) {
        Tile to = board.getTile(end);
        System.out.println(to);
        Piece movedPiece = to.getPiece();
        movedPiece.setPosition(start);
        board.getTile(start).setPiece(movedPiece);
        to.setPiece(null);
    }

    @Override
    public Move copy() {
        return new NormalMove(new Point(this.start), new Point(this.end), this.getType());
    }
}
