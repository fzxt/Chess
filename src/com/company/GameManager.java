package com.company;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.MoveHistory;
import com.company.piece.Piece;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    private static GameManager instance;
    private Player currentPlayer;

    private Player white;
    private Player black;

    private final Board board;

    private Piece selectedPiece;
    private ArrayList<AttackMoveLog> deadPieces;

    private GameManager() {
        this.white = new Player(Team.WHITE);
        this.black = new Player(Team.BLACK);
        this.currentPlayer = white;
        this.board = new Board(white, black);
        this.deadPieces = new ArrayList<>();
        this.selectedPiece = null;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public static GameManager getInstanceWithBoard(Board board) {
        if (instance == null) {
            instance = new GameManager(board);
        }

        return instance;
    }

    private GameManager(Board board) {
        this.white = new Player(Team.WHITE);
        this.black = new Player(Team.BLACK);
        this.currentPlayer = white;
        this.board = board;
        this.selectedPiece = null;
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

    public int getMoveCount() {
        return MoveHistory.getInstance().getMoveHistory().size();
    }

    public void toggleTeam() {
        currentPlayer = currentPlayer == white ? black : white;
    }

    public void undo() {
        MoveHistory.getInstance().popLastMove().undo(getBoard());
    }

    private class AttackMoveLog {
        Move attackMove;
        Piece piece;

        public AttackMoveLog(Move move, Piece piece) {
            this.attackMove = move;
            this.piece = piece;
        }

        public Move getAttackMove() {
            return attackMove;
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
