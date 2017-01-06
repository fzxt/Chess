package com.company.board;

public class Move {
    private Position start;
    private Position end;
    private MoveType type;
    
    public Move(Position start, Position end, MoveType type){
        this.start = start;
        this.end = end;
        this.type = type;
    }
    
    public MoveType getType() {
        return this.type;
    }

    public Position getStart() {
        return this.start;
    }

    public Position getEnd() {
        return this.end;
    }
        
    private class Position {
        public int x;
        public int y;
        
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
