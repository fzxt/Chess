package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;

import java.awt.*;

public class PawnPromotionMove extends NormalMove {

    public PawnPromotionMove(Point start, Point end) {
        super(start, end);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.YELLOW;
    }

    @Override
    public void undo(Board board) {
    }

    @Override
    public Move copy() {
        return new PawnPromotionMove(new Point(this.start), new Point(this.end));
    }
}
