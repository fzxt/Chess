package com.company.gui;

import com.company.board.Board;

public class BoardPresenter implements GUIContract.Presenter {
    private final Board board;
    private final GUIContract.View view;

    public BoardPresenter(GUIContract.View view, Board board) {
        this.board = board;
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
}
