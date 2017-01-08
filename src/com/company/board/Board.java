package com.company.board;

import com.company.Player;
import com.company.Team;
import com.company.piece.*;

import java.awt.Point;
import java.util.List;

import static com.company.board.Tile.TILE_HIGHLIGHT.NONE;

public class Board {
    private Tile[][] board;
<<<<<<< HEAD

    public Board() {
        board = new Tile[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile();
=======
    /**
     * Constructor, initializes board.
     */
    public Board(Player white, Player black) {
        board = new Tile[8][8];

        for (int i = 0; i < 8; i+=7) {
            Team team = i == 0 ? Team.BLACK : Team.WHITE;
            board[i][0] = new Tile(new Rook(team, new Point(0, i)));
            board[i][1] = new Tile(new Knight(team, new Point(1, i)));
            board[i][2] = new Tile(new Bishop(team, new Point(2, i)));
            board[i][3] = new Tile(new King(team, new Point(3, i)));
            board[i][4] = new Tile(new Queen(team, new Point(4, i)));
            board[i][5] = new Tile(new Bishop(team, new Point(5, i)));
            board[i][6] = new Tile(new Knight(team, new Point(6, i)));
            board[i][7] = new Tile(new Rook(team, new Point(7, i)));


            // TODO: Think of a better solution
            givePieceToCorrectPlayer(board[i][0].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][1].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][2].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][3].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][4].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][5].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][6].getPiece(), black, white);
            givePieceToCorrectPlayer(board[i][7].getPiece(), black, white);
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile(new Point(j, i));
>>>>>>> master
            }
        }
        board[1][4] = new Tile(new Pawn(Team.WHITE, new Point(1, 4)));
        board[2][4] = new Tile(new Pawn(Team.BLACK, new Point(2, 4)));

<<<<<<< HEAD
        List<Move> moves = board[2][4].getPiece().getAvailableMoves(this);
        System.out.println(moves);
        System.out.println(this);
=======
        for (int k = 0; k < 8; k++) {
            board[1][k] = new Tile(new Pawn(Team.BLACK, new Point(k, 1)));
            board[6][k] = new Tile(new Pawn(Team.WHITE, new Point(k, 6)));
        }
    }

    private void givePieceToCorrectPlayer(Piece piece, Player black, Player white) {
        if (piece.getTeam() == Team.BLACK) {
            black.addPiece(piece);
        } else {
            white.addPiece(piece);
        }
>>>>>>> master
    }

    public Tile[][] getBoard() {
        return this.board;
    }

    public Tile getTile(Point point) {
        return this.board[point.y][point.x];
    }

    public void setTile(Point position, Tile tile) {
        this.board[position.y][position.x] = tile;
    }

    public void unhighlightBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getHighlight() != NONE) {
                    board[i][j].setHighlight(NONE);
                }
            }
        }
    }

<<<<<<< HEAD
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
=======
}
>>>>>>> master
