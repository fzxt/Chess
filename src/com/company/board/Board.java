package com.company.board;

import com.company.Team;
import com.company.piece.*;
import java.awt.Point;

public class Board {

    private Tile[][] board;

    /**
     * Constructor, initializes board.
     */
    public Board() {
        board = new Tile[8][8];

        for (int i = 0; i < 8; i+=7) {
            Team team = i == 0 ? Team.BLACK : Team.WHITE;
            board[i][0] = new Tile(new Rook(team, new Point(i, 0)));
            board[i][1] = new Tile(new Knight(team, new Point(i, 1)));
            board[i][2] = new Tile(new Bishop(team, new Point(i, 2)));
            board[i][3] = new Tile(new King(team, new Point(i, 3)));
            board[i][4] = new Tile(new Queen(team, new Point(i, 4)));
            board[i][5] = new Tile(new Bishop(team, new Point(i, 5)));
            board[i][6] = new Tile(new Knight(team, new Point(i, 6)));
            board[i][7] = new Tile(new Rook(team, new Point(i, 7)));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile();
            }
        }

        for (int y = 0; y < 8; y++) {
            board[1][y] = new Tile(new Pawn(Team.BLACK, new Point(1, y)));
            board[6][y] = new Tile(new Pawn(Team.WHITE, new Point(6, y)));
        }
    }

    public Tile[][] getBoard() {
        return this.board;
    }

    public Tile getTile(Point point) {
        return this.board[point.x][point.y];
    }

}
