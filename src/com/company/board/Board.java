package com.company.board;

import com.company.Team;
import com.company.piece.*;

import java.awt.Point;
import java.util.List;

public class Board {
    private Tile[][] board;

    public Board() {
        board = new Tile[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile();
            }
        }
        board[1][4] = new Tile(new Pawn(Team.WHITE, new Point(1, 4)));
        board[2][4] = new Tile(new Pawn(Team.BLACK, new Point(2, 4)));

        List<Move> moves = board[2][4].getPiece().getAvailableMoves(this);
        System.out.println(moves);
        System.out.println(this);
    }

    public Tile[][] getBoard() {
        return this.board;
    }

    public Tile getTile(Point point) {
        return this.board[point.x][point.y];
    }

    public boolean validPosition(Point position) {
        return (position.x >= 0 && position.x <= 7 && position.y >= 0 && position.y <= 7);
    }

    public Tile getTile(int x, int y) {
        return this.board[x][y];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            sb.append("");
            sb.append("-----------------------------------------------------------------\n");
            for (int j = 0; j < board[i].length; j++) {
                sb.append("| " + board[j][i] + " ");
            }
            sb.append("|\n");
        }
        sb.append("-----------------------------------------------------------------\n");
        return sb.toString();
    }
}
