package com.company.move;

import com.company.board.Tile;

import java.awt.Point;

public abstract class Move {
    private Point start;
    private Point end;
    private MoveType type;
    
    public Move(Point start, Point end, MoveType type){
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public MoveType getType() {
        return this.type;
    }

    public Point getEnd() {
        return this.end;
    }

    public abstract Tile.TILE_HIGHLIGHT getTileHighlight();

    @Override
    public String toString() {
        return "S: " + start + "\t E: " + end;
    }
}
