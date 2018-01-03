package com.company.gui.board;

import com.company.Game;
import com.company.GameManager;
import com.company.Player;
import com.company.Team;
import com.company.ai.AI;
import com.company.board.*;
import com.company.move.Move;
import com.company.piece.Pawn;
import com.company.piece.Piece;
import com.company.piece.Queen;

import java.awt.*;

import static com.company.board.Tile.TILE_HIGHLIGHT.*;
import static com.company.move.MoveType.*;
import static com.company.piece.PieceType.*;

// See interface (BoardGUIContract) for comments

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
        if (gameManager.isGameOver()) {
            return;
        }

        if (!tile.isEmpty() && isClickablePiece(tile)) {
            // It means they clicked a tile with a piece and it's not highlighted
            // Or they clicked the king, who is in check
            boolean inCheck = tile.getHighlight() == ORANGE;
            gameManager.unhighlightBoard();
            Player currentPlayer = gameManager.getCurrentPlayer();
            Piece piece = tile.getPiece();

            if (inCheck) {
                if (piece.getType() != KING) {
                    // If they clicked a piece thats not a king while in check, don't show the moves
                    System.out.println("You can't move other pieces except your King while in check!");
                    return;
                } else if (piece.getType() == KING && piece.getAvailableMoves(gameManager.getBoard()).isEmpty()) {
                    handleGameOver(Team.BLACK);
                    return;
                }
            }

            if (currentPlayer.getTeam() == piece.getTeam()) {
                // Only show the current user their moves
                showAvailableMoves(piece);
                gameManager.setSelectedPiece(piece);
            }

            view.updateBoard(gameManager.getBoard());
        } else if (tile.isHighlighted() && isMove(tile)) {
            // It means they clicked a tile with a piece that is highlighted, i.e an attacking move.
            // Or they clicked a tile without a piece that is highlighted
            Move move = tile.getMove();

            if (handleKingCaptureGameOver(move)) {
                handleGameOver(Team.WHITE);
                move.handleMove(gameManager.getBoard());
                return;
            }

            move.handleMove(gameManager.getBoard());

            // Check for pawn promotion
            if (move.getType() == PAWN_PROMOTION) {
                Pawn pawn = (Pawn) gameManager.getSelectedPiece();
                if (pawn.promotePawn()) {
                    game.showPawnPromotionView();
                }
            }

            gameManager.unhighlightBoard();
            handleAIMove();

            if (gameManager.isGameOver()) {
                // Check if the AI ended the game already
                return;
            }

            gameManager.nextTurn();

            // Check if the AI put the user in check.
            if (gameManager.inCheck(Team.WHITE)) {
                // If so, highlight the tile orange
                // Orange is a special color, only used to signify in check
                Tile kingTile = gameManager.getTile(gameManager.whiteKingPosition);
                kingTile.setHighlight(ORANGE);
            }

            view.updateBoard(gameManager.getBoard());
        } else {
            gameManager.unhighlightBoard();
            view.updateBoard(gameManager.getBoard());
        }

    }

    private boolean handleKingCaptureGameOver(Move move) {
        if (move.getType() == ATTACK) {
            if (gameManager.getTile(move.getEnd()).getPiece().getType() == KING) {
                return true;
            }
        }

        return false;
    }

    private void handleGameOver(Team winner) {
        gameManager.endGame(winner);
        gameManager.printWinner();
        view.showOverlay();
    }

    private boolean isClickablePiece(Tile tile) {
        return !tile.isHighlighted() || tile.getHighlight() == ORANGE;
    }

    private void handleAIMove() {
        Move aiMove = ai.bestMove(gameManager.getBoard());
        if (handleKingCaptureGameOver(aiMove)) {
            // The AI has captured the king
            handleGameOver(Team.BLACK);
            aiMove.handleMove(gameManager.getBoard());
            return;
        } else {
            aiMove.handleMove(gameManager.getBoard());
        }

        if (aiMove.getType() == PAWN_PROMOTION) {
            Piece pawnToPromote = gameManager.getTile(aiMove.end).getPiece();
            Piece queen = new Queen(Team.BLACK, pawnToPromote.getPosition());
            queen.setPosition(pawnToPromote.getPosition());
            gameManager.getTile(pawnToPromote.getPosition()).setPiece(queen);
        }

        gameManager.nextTurn();
    }

    private void showAvailableMoves(Piece piece) {
        for (Move move : piece.getAvailableMoves(gameManager.getBoard())) {
            Point movePoint = new Point(move.getEnd().x, move.getEnd().y);
            Tile startTile = gameManager.getTile(piece.getPosition());
            startTile.setHighlight(GREEN);
            Tile target = gameManager.getTile(movePoint);
            target.setHighlight(move.getTileHighlight());
            target.setMove(move);
            gameManager.setTile(movePoint, target);
        }
    }

    public boolean isMove(Tile tile) {
        return tile.getHighlight() != GREEN && tile.getHighlight() != ORANGE;
    }
}