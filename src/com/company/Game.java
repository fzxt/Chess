package com.company;

import com.company.board.Board;
import com.company.gui.BoardPresenter;
import com.company.gui.BoardView;

import javax.swing.*;

public class Game {

    GameManager gameManager;

    public Game() {
        GameManager gameManager = new GameManager();
        new BoardPresenter(new BoardView(), gameManager);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}