package com.company.move;

import com.company.GameManager;
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
    public void undo(GameManager gm) {
        System.err.println("PawnPromotion: Undo not implemented!");
    }
}
