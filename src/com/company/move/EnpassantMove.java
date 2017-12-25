package com.company.move;

import com.company.GameManager;
import com.company.Team;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class EnpassantMove extends SpecialMove {
    public EnpassantMove(Point start, Point end) {
        super(start, end, MoveType.ENPASSANT);
    }

    @Override
    public void handleMove(GameManager gameManager, Tile target) {
        super.handleMove(gameManager, target);
        Point targetPosition = target.getPosition();
        int direction = gameManager.getCurrentPlayer().getTeam() == Team.WHITE ? 1 : -1;
        Piece attackedPiece = gameManager.getTile(targetPosition.x, targetPosition.y+direction).getPiece();
        // Clear tile
        gameManager.setTile(attackedPiece.getPosition(), new Tile(attackedPiece.getPosition()));
        gameManager.removePieceFromGame(attackedPiece);
    }

    @Override
    public void undo(GameManager gm) {
        System.err.println("Enpassant: Undo not implemented!");
    }
}
