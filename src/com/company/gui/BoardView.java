package com.company.gui;

import com.company.board.Board;
import com.company.board.Tile;
import com.company.gui.component.TilePanel;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardView extends MouseAdapter implements GUIContract.View  {

    private JPanel panel;
    private GUIContract.Presenter presenter;

    private final JFrame frame;

    private final TilePanel[][] tiles;
    private final GUIUtils GUIUtils;

    public BoardView() {
        this.frame = new JFrame("Chess");
        this.panel = new JPanel(new GridLayout(8, 8));
        this.tiles = new TilePanel[8][8];
        GUIUtils = new GUIUtils();
    }

    @Override
    public void setPresenter(GUIContract.Presenter presenter) {
        this.presenter = presenter;
        presenter.start();
    }

    @Override
    public void showView() {
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(650, 650));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void updateBoard(Board newBoard) {
        frame.getContentPane().removeAll();
        panel = new JPanel(new GridLayout(8, 8));
        showBoard(newBoard);
        frame.getContentPane().add(panel);
        frame.pack();
    }

    @Override
    public void showBoard(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTile(new Point(j, i));
                tiles[i][j] = new TilePanel(tile);
                tiles[i][j].addMouseListener(this);
                panel.add(tiles[i][j]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Tile tileClicked = GUIUtils.getRelevantTile(tiles, e);
        presenter.handleClickedTile(tileClicked);
    }
}

