package com.company.gui;

import com.company.GameManager;
import com.company.Player;
import com.company.Team;
import com.company.board.*;
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
        // If they clicked their own piece AND it's their turn, they want to move it, show the moves.
        if (currentPlayer.getTeam() == piece.getTeam()) {
            gameManager.unhighlightBoard();

            for (Move move : piece.getAvailableMoves(gameManager.getBoard())) {
                Point movePoint = new Point(move.getEnd().x, move.getEnd().y);
                Tile startTile = gameManager.getBoard().getTile(piece.getPosition());
                startTile.setHighlight(Tile.TILE_HIGHLIGHT.GREEN);
                Tile moveableTile = gameManager.getBoard().getTile(movePoint);

                switch (move.getType()) {
                    case NORMAL:
                    case NORMAL_DOUBLE:
                        moveableTile.setHighlight(Tile.TILE_HIGHLIGHT.BLUE);
                        break;
                    case ATTACK:
                        moveableTile.setHighlight(Tile.TILE_HIGHLIGHT.RED);
                        break;
                    case ENPASSANT:
                    case CASTLE:
                        moveableTile.setHighlight(Tile.TILE_HIGHLIGHT.YELLOW);
                        break;
                }

                moveableTile.setMove(move);
                gameManager.setTile(movePoint, moveableTile);
            }

            gameManager.setSelectedPiece(piece);
            view.updateBoard(gameManager.getBoard());
        } else {
            gameManager.unhighlightBoard();
            view.updateBoard(gameManager.getBoard());
        }
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
            gameManager.setTile(attackedPiece.getPosition(), new Tile(attackedPiece.getPosition()));
        }

        gameManager.removePieceFromGame(attackedPiece);

        // Finally unhighlight the board and update.
        gameManager.getBoard().unhighlightBoard();
        view.updateBoard(gameManager.getBoard());
        gameManager.nextTurn();
    }

    private void makeMove(Tile tileToMoveTo) {
        Move move = tileToMoveTo.getMove();
        Piece pieceToMove = gameManager.getSelectedPiece();

        // 1. Set the piece to move tile to empty.
        gameManager.getBoard().setTile(pieceToMove.getPosition(), new Tile(pieceToMove.getPosition()));

        // 2. Set the move location tile to the piece.
        pieceToMove.move(move);
        tileToMoveTo.setPiece(pieceToMove);
        gameManager.setTile(tileToMoveTo.getPosition(), tileToMoveTo);
    }

}
