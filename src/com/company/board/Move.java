package com.company.board;

import java.awt.Point;

public class Move {
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

    public Point getStart() {
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }
}
