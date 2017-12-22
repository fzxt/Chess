package com.company.move;

import com.company.board.Tile;

import java.awt.*;

public class SpecialMove extends Move {

    public SpecialMove(Point start, Point end, MoveType type) {
        super(start, end, type);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.YELLOW;
    }
}
