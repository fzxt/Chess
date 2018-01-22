package com.company.move;

import com.company.board.Board;
import com.company.board.Tile;

import java.awt.Point;

public abstract class Move {
    public Point start;
    public Point end;
    private MoveType type;

    /**
     * Move constructor
     * @param start     start position (where is the piece now)
     * @param end       end position of move (where you want the piece to end up)
     * @param type      type of move, attack, special, enpassant, etc
     */
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
    
    public Point getStart(){
        return this.start;   
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move) {
            Move move = (Move) obj;
            return this.start == move.start && this.end == move.end && this.type == move.type;
        }

        return false;
    }

    /**
     * Method to handle a move
     * @param board     board to handle move on, change
     */
    public void handleMove(Board board) {
        board.handleMove(this);
    }

    /**
     * Gets the tile highlight for the GUI
     * @return          tile highlight
     */
    public abstract Tile.TILE_HIGHLIGHT getTileHighlight();

    @Override
    public String toString() {
        return "S: " + start + "\t E: " + end;
    }

    public abstract void undo(Board board);

    public abstract Move copy();
}
