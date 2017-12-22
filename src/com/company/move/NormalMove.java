package com.company.move;

import com.company.board.Tile;

import java.awt.*;

public class NormalMove extends Move{

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
}
