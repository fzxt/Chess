package com.company.board;

import com.company.Team;
import com.company.piece.*;

public class Board {

    private Tile[][] board;


    public Board() {
        board = new Tile[8][8];

        board[0][0] = new Tile(new Rook(Team.BLACK, 5, PieceType.ROOK));
        board[0][1] = new Tile(new Knight(Team.BLACK, 3, PieceType.KNIGHT));
        board[0][2] = new Tile(new Bishop(Team.BLACK, 3, PieceType.BISHOP));
        board[0][3] = new Tile(new King(Team.BLACK, Integer.MAX_VALUE, PieceType.KING));
        board[0][4] = new Tile(new Queen(Team.BLACK, 3, PieceType.QUEEN));
        board[0][5] = new Tile(new Bishop(Team.BLACK, 3, PieceType.BISHOP));
        board[0][6] = new Tile(new Knight(Team.BLACK, 3, PieceType.KNIGHT));
        board[0][7] = new Tile(new Rook(Team.BLACK, 5, PieceType.ROOK));

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Tile();
            }
        }

        for (int y = 0; y < 7; y++) {
            board[1][y] = new Tile(new Pawn(Team.BLACK, 1, PieceType.PAWN));
            board[6][y] = new Tile(new Pawn(Team.BLACK, 1, PieceType.PAWN));
        }


        board[7][0] = new Tile(new Rook(Team.BLACK, 5, PieceType.ROOK));
        board[7][1] = new Tile(new Knight(Team.BLACK, 3, PieceType.KNIGHT));
        board[7][2] = new Tile(new Bishop(Team.BLACK, 3, PieceType.BISHOP));
        board[7][3] = new Tile(new King(Team.BLACK, Integer.MAX_VALUE, PieceType.KING));
        board[7][4] = new Tile(new Queen(Team.BLACK, 3, PieceType.QUEEN));
        board[7][5] = new Tile(new Bishop(Team.BLACK, 3, PieceType.BISHOP));
        board[7][6] = new Tile(new Knight(Team.BLACK, 3, PieceType.KNIGHT));
        board[7][7] = new Tile(new Rook(Team.BLACK, 5, PieceType.ROOK));
    }

}
