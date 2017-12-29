package com.company.gui.board;

import com.company.Game;
import com.company.GameManager;
import com.company.Player;
import com.company.ai.AI;
import com.company.board.*;
import com.company.move.Move;
import com.company.piece.Pawn;
import com.company.piece.Piece;
import com.company.piece.PieceType;

import java.awt.*;

public class BoardPresenter implements BoardGUIContract.Presenter {
    private final BoardGUIContract.View view;
    private final GameManager gameManager;
    private final Game game;
    private final AI ai;

    public BoardPresenter(BoardGUIContract.View view, GameManager gameManager, Game game, AI ai) {
        this.gameManager = gameManager;
        this.view = view;
        this.game = game;
        this.ai = ai;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadBoard();
    }

    @Override
    public void loadBoard() {
        view.setBoard(gameManager.getBoard());
        view.showView();
    }

    @Override
    public void pause() {
        view.setEnabled(false);
        view.showOverlay();
    }

    @Override
    public void unpause() {
        view.setEnabled(true);
        view.hideOverlay();
        view.updateBoard(gameManager.getBoard());
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
            move.handleMove(gameManager.getBoard());

            // Check for pawn promotion
            if (gameManager.getSelectedPiece().getType() == PieceType.PAWN) {
                Pawn pawn = (Pawn) gameManager.getSelectedPiece();
                if (pawn.promotePawn()) {
                    game.showPawnPromotionView();
                }
            }

            gameManager.unhighlightBoard();
            handleAIMove();
            gameManager.nextTurn();
            view.updateBoard(gameManager.getBoard());
        } else {
            gameManager.unhighlightBoard();
            view.updateBoard(gameManager.getBoard());
        }

    }

    private void handleAIMove() {
        Move aiMove = ai.bestMove(gameManager.getBoard());
        aiMove.handleMove(gameManager.getBoard());
        gameManager.nextTurn();
    }

    private void showAvailableMoves(Piece piece) {
        for (Move move : piece.getAvailableMoves(gameManager.getBoard())) {
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