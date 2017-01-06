package com.company.gui;

import com.company.board.Board;

public interface GUIContract {

    interface Presenter {
        void start();
        void loadBoard();
    }

    interface View {
        void setPresenter(Presenter presenter);
        void showBoard(Board board);
        void showView();
    }





}
