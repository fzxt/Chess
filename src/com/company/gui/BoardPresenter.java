package com.company.gui;

import com.company.GameManager;
import com.company.Player;
import com.company.board.*;
import com.company.move.Move;
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
            gameManager.unhighlightBoard();
            Player currentPlayer = gameManager.getCurrentPlayer();
            Piece piece = tile.getPiece();

            if (currentPlayer.getTeam() == piece.getTeam()) {
                showAvailableMoves(piece);
                gameManager.setSelectedPiece(piece);
            }

            view.updateBoard(gameManager.getBoard());
        } else if (tile.isHighlighted() && tile.getHighlight() != Tile.TILE_HIGHLIGHT.GREEN) {
            // It means they clicked a tile with a piece that is highlighted, i.e an attacking move.
            // Or they clicked a tile without a piece that is highlighted
            Move move = tile.getMove();
            move.handleMove(gameManager, tile);
            gameManager.unhighlightBoard();
            gameManager.nextTurn();
            view.updateBoard(gameManager.getBoard());
        } else {
            gameManager.unhighlightBoard();
            view.updateBoard(gameManager.getBoard());
        }

    }

    private void showAvailableMoves(Piece piece) {
        for (Move move : piece.getAvailableMoves(gameManager.getBoard())) {
            System.out.println(move);
            Point movePoint = new Point(move.getEnd().x, move.getEnd().y);
            Tile startTile = gameManager.getTile(piece.getPosition());
            startTile.setHighlight(Tile.TILE_HIGHLIGHT.GREEN);
            Tile target = gameManager.getTile(movePoint);
            target.setHighlight(move.getTileHighlight());
            target.setMove(move);
            gameManager.setTile(movePoint, target);
        }
    }
}