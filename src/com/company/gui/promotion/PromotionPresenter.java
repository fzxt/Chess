package com.company.gui.promotion;

import com.company.Game;
import com.company.GameManager;
import com.company.Team;
import com.company.board.Tile;
import com.company.piece.Piece;

// See interface (PromotionGUIContract) for comments

public class PromotionPresenter implements PromotionGUIContract.Presenter {

    private final PromotionGUIContract.View view;
    private final GameManager gameManager;
    private final Game game;

    public PromotionPresenter(PromotionGUIContract.View view, GameManager gameManager, Game game) {
        this.view = view;
        this.view.setPresenter(this);
        this.gameManager = gameManager;
        this.game = game;
    }

    public void loadView() {
        view.showView();
    }

    @Override
    public void handlePromotion(Piece selectedPiece) {
        Piece pawnToPromote = gameManager.getSelectedPiece();
        selectedPiece.setPosition(pawnToPromote.getPosition());
        gameManager.setTile(pawnToPromote.getPosition(), new Tile(selectedPiece));
        view.hideView();
        game.unpause();
    }

    @Override
    public Team getTeam() {
        return gameManager.getCurrentPlayer().getTeam();
    }
}
