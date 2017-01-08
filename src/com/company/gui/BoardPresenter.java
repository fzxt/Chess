package com.company.gui;

import com.company.GameManager;
import com.company.Player;
import com.company.Team;
import com.company.board.*;
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
        if (!tile.isEmpty() && !tile.isHighlighted()) {
            // It means they clicked a tile with a piece and it's not highlighted, show available moves
            handleAvailableMoves(tile);
        } else if (tile.isHighlighted()) {
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
            board.unhighlightBoard();
            view.updateBoard(board);
        }
    }



    private void handleAvailableMoves(Tile tile) {
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
                board.setTile(movePoint, moveableTile);
            }

            gameManager.setSelectedPiece(piece);
            view.updateBoard(board);
        } else {
            board.unhighlightBoard();
            view.updateBoard(board);
        }
    }

    private void handleNormalMove(Tile tileToMoveTo) {
        Move move = tileToMoveTo.getMove();
        Piece pieceToMove = gameManager.getSelectedPiece();

        // 1. Set the piece to move tile to empty.
        board.setTile(pieceToMove.getPosition(), new Tile(pieceToMove.getPosition()));

        // 2. Set the move location tile to the attacking piece.
        pieceToMove.move(move);
        tileToMoveTo.setPiece(pieceToMove);
        board.setTile(tileToMoveTo.getPosition(), tileToMoveTo);
        MoveHistory.addMove(move);

        // Finally unhighlight the board and update.
        board.unhighlightBoard();
        view.updateBoard(board);
        gameManager.nextTurn();
    }

    private void handleAttack(Tile tileToMoveTo) {
        Point tilePos = tileToMoveTo.getPosition();
        Piece pieceToMove = gameManager.getSelectedPiece();
        Piece attackedPiece = null;

        if (tileToMoveTo.getMove().getType() == MoveType.ATTACK) {
            attackedPiece = tileToMoveTo.getPiece();
        }

        Move move = tileToMoveTo.getMove();

        // 1. Set the piece to move tile to empty.
        board.setTile(pieceToMove.getPosition(), new Tile(pieceToMove.getPosition()));

        // 2. Set the move location tile to the attacking piece.
        pieceToMove.move(move);
        tileToMoveTo.setPiece(pieceToMove);
        board.setTile(tileToMoveTo.getPosition(), tileToMoveTo);
        MoveHistory.addMove(move);

        // 3. If it's enpassant, you need to set the attackedPiece tile to empty.
        if (move.getType() == MoveType.ENPASSANT) {
            int direction = gameManager.getCurrentPlayer().getTeam() == Team.WHITE ? 1 : -1;
            attackedPiece = board.getTile(tilePos.x, tilePos.y+direction).getPiece();
            board.setTile(attackedPiece.getPosition(), new Tile(attackedPiece.getPosition()));
        }

        gameManager.removePieceFromGame(attackedPiece);

        // Finally unhighlight the board and update.
        board.unhighlightBoard();
        view.updateBoard(board);
        gameManager.nextTurn();
    }

}
