package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;

import java.awt.Point;

public abstract class Move {
    public Point start;
    public Point end;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move) {
            Move move = (Move) obj;
            return this.start == move.start && this.end == move.end && this.type == move.type;
        }

        return false;
    }

    public void handleMove(Board board) {
        board.handleMove(this);
    }

    public abstract Tile.TILE_HIGHLIGHT getTileHighlight();

    @Override
    public String toString() {
        return "S: " + start + "\t E: " + end;
    }

    public abstract void undo(Board board);

    public abstract Move copy();
}
