package com.company.move;

import com.company.board.Tile;

import java.awt.*;

public abstract class SpecialMove extends Move {

    SpecialMove(Point start, Point end, MoveType type) {
        super(start, end, type);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.YELLOW;
    }
}
