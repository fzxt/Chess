package com.company;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.MoveHistory;
import com.company.piece.Piece;
import com.company.piece.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    private static GameManager instance;
    public Point whiteKingPosition;
    public Point blackKingPosition;
    private Player currentPlayer;

    private Player white;
    private Player black;

    private final Board board;

    private Piece selectedPiece;
    private ArrayList<AttackMoveLog> deadPieces;
    private boolean gameOver;
    private Team winner;

    private GameManager() {
        this.white = new Player(Team.WHITE);
        this.black = new Player(Team.BLACK);
        this.currentPlayer = white;
        this.board = new Board(white, black);
        this.blackKingPosition = new Point(4, 0);
        this.whiteKingPosition = new Point(4, 7);
        this.gameOver = false;
        this.winner = null;
        this.deadPieces = new ArrayList<>();
        this.selectedPiece = null;
    }

    private GameManager(Board board) {
        this.white = new Player(Team.WHITE);
        this.black = new Player(Team.BLACK);
        this.currentPlayer = white;
        this.board = board;
        findAndUpdateKingPosition(this.board);
        this.selectedPiece = null;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    private void findAndUpdateKingPosition(Board board) {
        for (Tile[] tiles : board.getBoard()) {
            for (Tile tile : tiles) {
                if (!tile.isEmpty()) {
                    Piece piece = tile.getPiece();
                    if (piece.getType() == PieceType.KING) {
                        updateKingPosition(piece);
                    }
                }
            }
        }
    }

    public boolean inCheck(Team team) {
        Point kingPosition = team == Team.WHITE ? whiteKingPosition : blackKingPosition;
        // If the tile of the players king is threatened, then we are in check.
        return board.tileIsThreatened(team, board.getTile(kingPosition));
    }

    public void removePieceFromGame(Move move, Piece piece) {
        if (white.pieces.contains(piece)) {
            white.removePiece(piece);
        } else if (black.pieces.contains(piece)) {
            black.removePiece(piece);
        }

        deadPieces.add(new AttackMoveLog(move, piece));
        piece.setPosition(new Point(-1, -1));
    }

    public Piece getDeadPieceFromMove(Move move) {
        for (AttackMoveLog logs : deadPieces) {
            if (logs.attackMove == move) {
                return logs.getPiece();
            }
        }

        return null;
    }

    public void unhighlightBoard() {
        board.unhighlightBoard();
    }

    public void setTile(Point point, Tile tile) {
        board.setTile(point, tile);
    }

    public void nextTurn() {
        currentPlayer = currentPlayer == white ? black : white;
    }

    public Tile getTile(int x, int y) {
        return getBoard().getTile(x, y);
    }

    public Tile getTile(Point pos) {
        return getBoard().getTile(pos);
    }

    public void undo() {
        MoveHistory.getInstance().popLastMove().undo(getBoard());
    }

    public void updateKingPosition(Piece king) {
        if (king.getTeam() == Team.WHITE) {
            this.whiteKingPosition = king.getPosition();
        } else {
            this.blackKingPosition = king.getPosition();
        }
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void endGame(Team winner) {
        this.gameOver = true;
        this.winner = winner;
    }

    public void printWinner() {
        System.out.println("The winner is TEAM " + winner);
    }

    private class AttackMoveLog {
        Move attackMove;
        Piece piece;

        public AttackMoveLog(Move move, Piece piece) {
            this.attackMove = move;
            this.piece = piece;
        }

        public Piece getPiece() {
            return piece;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof AttackMoveLog) {
                AttackMoveLog log = (AttackMoveLog) obj;
                return log.attackMove.start == this.attackMove.start &&
                        log.attackMove.end == this.attackMove.end &&
                        log.piece.getType() == this.piece.getType() &&
                        log.piece.getTeam() == this.piece.getTeam();
            }

            return false;
        }
    }
}
