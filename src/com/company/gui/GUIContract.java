package com.company.gui;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;
import java.util.ArrayList;

public interface GUIContract {
    interface Presenter {
        /**
         * This will start to load the initial board. Called by the view.
         */
        void start();

        /**
         * This will load the initial board.
         */
        void loadBoard();

        /**
         * This will handle clicking a tile.
         * @param tileClicked   tile that was clicked.
         */
        void handleClickedTile(Tile tileClicked);
    }

    interface View {
        /**
         * This attaches the presenter to the view.
         * Called by the presenter, once it's done initializing.
         * @param presenter     presenter to attach.
         */
        void setPresenter(Presenter presenter);

        /**
         * This will show the board on the screen.
         * @param board         board to show.
         */
        void showBoard(Board board);

        /**
         * Sets up the JFrame and shows the view.
         */
        void showView();

        /**
         * Updates and redraw the board.
         * @param board         new board.
         */
        void updateBoard(Board board);
    }
}
