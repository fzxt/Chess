package com.company.board;

public class Move {

    private Point currMove;
    private Point futMove;

    public Move(Point currMove, Point futMove) {
        this.currMove = currMove;
        this.futMove = futMove;
    }

    private class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
