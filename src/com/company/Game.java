package com.company;

import com.company.ai.AI;
import com.company.gui.board.BoardPresenter;
import com.company.gui.board.BoardView;
import com.company.gui.promotion.PromotionPresenter;
import com.company.gui.promotion.PromotionView;

import javax.swing.*;
import java.util.Scanner;

/**
 * Entry point of the program
 */

public class Game {
    
    //I know your gonna get this to work with giant depths so you need it configurable!!
    public static final int MAX_DEPTH = 4;
    public static final int MIN_DEPTH = 1;

    //Getters and setters?? Hughes would be so sad :(
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

    /**
        Unpauses the boardPresenter, not the Game. Despite this method being in Game. I am a lying method. I lie.
    */
    public void unpause() {
        boardPresenter.unpause();
    }

    public static void main(String[] args) {
        int depth = 0;
        while (true) {
            depth = askUserForDepth();
            if (depth >= MIN_DEPTH && depth <= MAX_DEPTH) {
                break;
            } else {
                System.out.printf("Invalid, please enter between %d - %d for depth %n",MIN_DEPTH,MAX_DEPTH);
            }
        }

        //int finalDepth = depth; No need
        SwingUtilities.invokeLater(() -> new Game(depth));
    }

    private static int askUserForDepth() {
        Scanner s = new Scanner(System.in);
        System.out.printf("What depth would you like for the AI? (max %d): ", MAX_DEPTH);
        return s.nextInt();
    }


}
