package com.company;

import com.company.ai.AI;
import com.company.gui.board.BoardPresenter;
import com.company.gui.board.BoardView;
import com.company.gui.promotion.PromotionPresenter;
import com.company.gui.promotion.PromotionView;

import javax.swing.*;
import java.util.Scanner;

public class Game {

    GameManager gameManager;
    BoardPresenter boardPresenter;
    PromotionPresenter promotionPresenter;

    public Game(int depth) {
        gameManager = GameManager.getInstance();
        AI ai = new AI(depth);
        boardPresenter = new BoardPresenter(new BoardView(), gameManager, this, ai);
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
        int depth = 0;
        while (true) {
            depth = askUserForDepth();
            if (depth >= 1 && depth <= 4) {
                break;
            } else {
                System.out.println("Invalid, please enter between 1 - 4 for depth");
            }
        }

        int finalDepth = depth;
        SwingUtilities.invokeLater(() -> new Game(finalDepth));
    }

    private static int askUserForDepth() {
        Scanner s = new Scanner(System.in);
        System.out.print("What depth would you like for the AI? (max 4): ");
        return s.nextInt();
    }


}