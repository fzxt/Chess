package com.company.gui.promotion;

import com.company.Team;
import com.company.board.Tile;
import com.company.gui.GUIUtils;
import com.company.gui.component.TilePanel;
import com.company.piece.Bishop;
import com.company.piece.Knight;
import com.company.piece.Queen;
import com.company.piece.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// See interface (PromotionGUIContract) for comments

public class PromotionView extends MouseAdapter implements PromotionGUIContract.View {

    private final JFrame frame;
    private final Team team;
    private JPanel panel;
    private PromotionGUIContract.Presenter presenter;
    private TilePanel[] selectableTiles;

    public PromotionView(Team team) {
        this.frame = new JFrame("Choose a piece to promote to");
        this.panel = new JPanel(new GridLayout(1, 3));
        this.team = team;
        setupView();
    }

    @Override
    public void setPresenter(PromotionGUIContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showView() {
        frame.setVisible(true);
    }

    @Override
    public void hideView() {
        frame.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Tile tileSelected = GUIUtils.getRelevantTile(selectableTiles, e);
        presenter.handlePromotion(tileSelected.getPiece());
    }

    private void setupView() {
        this.selectableTiles = new TilePanel[] {
                new TilePanel(new Tile(new Queen(team, new Point(0, 0)))),
                new TilePanel(new Tile(new Rook(team, new Point(0, 1)))),
                new TilePanel(new Tile(new Bishop(team, new Point(0, 0)))),
                new TilePanel(new Tile(new Knight(team, new Point(0, 1))))
        };

        for (TilePanel tilePanel : selectableTiles) {
            tilePanel.addMouseListener(this);
            panel.add(tilePanel);
        }

        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(300, 100));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
    }

}