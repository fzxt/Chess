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

            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile(new Rook(team, new Point(i, j)));
                board[i][j] = new Tile(new Knight(team, new Point(i, j)));
                board[i][j] = new Tile(new Bishop(team, new Point(i, j)));
                board[i][j] = new Tile(new King(team, new Point(i, j)));
                board[i][j] = new Tile(new Queen(team, new Point(i, j)));
                board[i][j] = new Tile(new Bishop(team, new Point(i, j)));
                board[i][j] = new Tile(new Knight(team, new Point(i, j)));
                board[i][j] = new Tile(new Rook(team, new Point(i, j)));
            }
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Tile();
            }
        }

        for (int y = 0; y < 7; y++) {
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
