package com.company.gui;

import com.company.GameManager;
import com.company.Player;
import com.company.board.Board;
import com.company.board.Move;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class BoardPresenter implements GUIContract.Presenter {
    private final Board board;
    private final GUIContract.View view;
    private final GameManager gameManager;

    public BoardPresenter(GUIContract.View view, GameManager gameManager) {
        this.gameManager = gameManager;
        this.board = gameManager.getBoard();
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadBoard();
    }

    @Override
    public void loadBoard() {
        view.showBoard(board);
        view.showView();
    }

    @Override
    public void handleClickedTile(Tile tile) {

        // It means they clicked a tile with a piece...
        if (!tile.isEmpty()) {
            Piece piece = tile.getPiece();
            Player currentPlayer = gameManager.getCurrentPlayer();
            // If they clicked their own piece AND it's their turn, they want to move it, show the moves.
            if (currentPlayer.getTeam() == piece.getTeam()) {
                board.unhighlightBoard();
                for (Move move : piece.getAvailableMoves(board)) {
                    Point movePoint = new Point(move.getEnd().x, move.getEnd().y);
                    Tile startTile = board.getTile(piece.getPosition());
                    startTile.setHighlight(Tile.TILE_HIGHLIGHT.GREEN);
                    Tile moveableTile = board.getTile(movePoint);

                    switch (move.getType()) {
                        case NORMAL:
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

                    board.setTile(movePoint, moveableTile);
                }

                view.updateBoard(board);
            } else {
                board.unhighlightBoard();
                view.updateBoard(board);
            }
        } else if (tile.isHiglighted()) {
            board.unhighlightBoard();

            // TODO: Make a move here.

            view.updateBoard(board);
            gameManager.nextTurn();
        }
    }
}
