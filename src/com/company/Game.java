package com.company;

import com.company.gui.board.BoardPresenter;
import com.company.gui.board.BoardView;
import com.company.gui.promotion.PromotionPresenter;
import com.company.gui.promotion.PromotionView;

import javax.swing.*;

public class Game {

    GameManager gameManager;
    BoardPresenter boardPresenter;
    PromotionPresenter promotionPresenter;

    public Game() {
        gameManager = new GameManager();
        boardPresenter = new BoardPresenter(new BoardView(), gameManager, this);
    }

    public void showPawnPromotionView() {
        promotionPresenter = new PromotionPresenter(new PromotionView(gameManager.getCurrentPlayer().getTeam()), gameManager, this);
        boardPresenter.pause();
        promotionPresenter.loadView();
    }

    public void unpause() {
        boardPresenter.unpause();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }

}