package com.company.move;

import com.company.GameManager;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.Point;

public abstract class Move {
    public Point start;
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

    public Point getEnd() {
        return this.end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move) {
            Move move = (Move) obj;
            return this.start == move.start && this.end == move.end && this.type == move.type;
        }

        return false;
    }

    public void handleMove(GameManager gameManager, Tile target) {
        Piece pieceToMove = gameManager.getSelectedPiece();
        // 1. Set the piece to move tile to empty.
        gameManager.getBoard().setTile(pieceToMove.getPosition(), new Tile(pieceToMove.getPosition()));

        // 2. Set the move location tile to the piece.
        pieceToMove.move(this);
        target.setPiece(pieceToMove);
    }

    public abstract Tile.TILE_HIGHLIGHT getTileHighlight();

    @Override
    public String toString() {
        return "S: " + start + "\t E: " + end;
    }
}
