package com.company.gui;

import com.company.GameManager;
import com.company.Player;
import com.company.Team;
import com.company.board.*;
import com.company.move.Move;
import com.company.move.MoveType;
import com.company.piece.Piece;

import java.awt.*;

public class BoardPresenter implements GUIContract.Presenter {
    private final GUIContract.View view;
    private final GameManager gameManager;

    public BoardPresenter(GUIContract.View view, GameManager gameManager) {
        this.gameManager = gameManager;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadBoard();
    }

    @Override
    public void loadBoard() {
        view.showBoard(gameManager.getBoard());
        view.showView();
    }

    @Override
    public void handleClickedTile(Tile tile) {
        if (!tile.isEmpty() && !tile.isHighlighted()) {
            // It means they clicked a tile with a piece and it's not highlighted, show available moves
            handleAvailableMoves(tile);
        } else if (tile.isHighlighted() && tile.getHighlight() != Tile.TILE_HIGHLIGHT.GREEN) {
            // It means they clicked a tile with a piece that is highlighted, i.e an attacking move.
            // Or they clicked a tile without a piece that is highlighted
            Move move = tile.getMove();

            // TODO: Get rid of this!
            switch (move.getType()) {
                case NORMAL:
                case NORMAL_DOUBLE:
                    handleNormalMove(tile);
                    break;
                case ATTACK:
                case ENPASSANT:
                    handleAttack(tile);
                    break;

            }
        } else {
            gameManager.unhighlightBoard();
            view.updateBoard(gameManager.getBoard());
        }
    }



    private void handleAvailableMoves(Tile tile) {
        Piece piece = tile.getPiece();
        Player currentPlayer = gameManager.getCurrentPlayer();
        gameManager.unhighlightBoard();
        // If they clicked their own piece AND it's their turn, they want to move it, show the moves.
        if (currentPlayer.getTeam() == piece.getTeam()) {

            for (Move move : piece.getAvailableMoves(gameManager.getBoard())) {
                Point movePoint = new Point(move.getEnd().x, move.getEnd().y);
                Tile startTile = gameManager.getBoard().getTile(piece.getPosition());
                startTile.setHighlight(Tile.TILE_HIGHLIGHT.GREEN);
                Tile target = gameManager.getBoard().getTile(movePoint);
                target.setHighlight(move.getTileHighlight());
                target.setMove(move);
                gameManager.setTile(movePoint, target);
            }

            gameManager.setSelectedPiece(piece);
        }

        view.updateBoard(gameManager.getBoard());
    }

    private void handleNormalMove(Tile tileToMoveTo) {
        makeMove(tileToMoveTo);
        // Finally unhighlight the board and update.
        gameManager.unhighlightBoard();
        view.updateBoard(gameManager.getBoard());
        gameManager.nextTurn();
    }

    private void handleAttack(Tile tileToMoveTo) {
        Point tilePos = tileToMoveTo.getPosition();
        Piece attackedPiece = null;

        if (tileToMoveTo.getMove().getType() == MoveType.ATTACK) {
            attackedPiece = tileToMoveTo.getPiece();
        }

        makeMove(tileToMoveTo);

        if (tileToMoveTo.getMove().getType() == MoveType.ENPASSANT) {
            int direction = gameManager.getCurrentPlayer().getTeam() == Team.WHITE ? 1 : -1;
            attackedPiece = gameManager.getTile(tilePos.x, tilePos.y+direction).getPiece();
            // Clear tile
            gameManager.setTile(attackedPiece.getPosition(), new Tile(attackedPiece.getPosition()));
        }

        gameManager.removePieceFromGame(attackedPiece);

        // Finally unhighlight the board and update.
        gameManager.getBoard().unhighlightBoard();
        view.updateBoard(gameManager.getBoard());
        gameManager.nextTurn();
    }

    private void makeMove(Tile target) {
        Move move = target.getMove();
        Piece pieceToMove = gameManager.getSelectedPiece();

        // 1. Set the piece to move tile to empty.
        gameManager.getBoard().setTile(pieceToMove.getPosition(), new Tile(pieceToMove.getPosition()));

        // 2. Set the move location tile to the piece.
        pieceToMove.move(move);
        target.setPiece(pieceToMove);
        gameManager.setTile(target.getPosition(), target);
    }

}
