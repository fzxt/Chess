package com.company.move;

import com.company.board.Tile;

import java.awt.*;

public class AttackMove extends Move {
    public AttackMove(Point start, Point end) {
        super(start, end, MoveType.ATTACK);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.RED;
    }
}
