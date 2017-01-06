package com.company.gui;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.gui.component.TilePanel;
import com.company.piece.Piece;

import javax.swing.*;
import java.awt.*;

import static com.company.gui.GUIUtils.*;

public class BoardView implements GUIContract.View {

    private final JPanel panel;
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
    public void showBoard(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean bothColumnRowEven = (i % 2 == 0 && j % 2 == 0);
                boolean bothColumnRowOdd = (i % 2 == 1 && j % 2 == 1);
                TILE_TYPE tileType = (bothColumnRowEven || bothColumnRowOdd) ? TILE_TYPE.LIGHT : TILE_TYPE.DARK;

                Tile tile = board.getTile(new Point(i, j));

                if (tile.isEmpty()) {
                    tiles[i][j] = new TilePanel(GUIUtils.getTileIcon(tileType));
                } else {
                    Piece tilePiece = tile.getPiece();
                    tiles[i][j] = new TilePanel(GUIUtils.getTileIcon(tileType),
                            GUIUtils.getPieceIcon(tilePiece.getTeam(), tilePiece.getType()));
                }

                panel.add(tiles[i][j]);
            }
        }
    }
}

