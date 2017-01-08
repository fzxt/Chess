package com.company.gui.component;


import com.company.board.Tile;
import com.company.gui.GUIUtils;
import com.company.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel {

    private BufferedImage pieceImage;
    private BufferedImage tileImage;
    private Tile tile;
    private GUIUtils utils;

    public TilePanel(Tile tile) {
        try {
            this.utils = new GUIUtils();
            this.tile = tile;
            String tileImageIconPath = utils.getTileIcon(tile.getColor());
            tileImage = ImageIO.read(new File(getClass().getResource(tileImageIconPath).getFile()));
            if (!tile.isEmpty()) {
                Piece piece = tile.getPiece();
                String pieceImageIconPath = utils.getPieceIcon(piece.getTeam(), piece.getType());
                pieceImage = ImageIO.read(new File(getClass().getResource(pieceImageIconPath).getFile()));
            }

            if (tile.getHighlight() != Tile.TILE_HIGHLIGHT.NONE) {
                tileImage = utils.getHighlighted(tileImage, tile.getHighlight());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(25, 25);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int x = getWidth() - tileImage.getWidth();
        int y = getHeight() - tileImage.getHeight();


        if (tileImage != null) {
            g2d.drawImage(tileImage, x, y, this);
        }

        if (pieceImage != null) {
            g2d.translate(tileImage.getWidth() / 2, tileImage.getHeight() / 2);
            g2d.drawImage(pieceImage, x, y, tileImage.getWidth() / 2, tileImage.getHeight() / 2, this);
        }
    }

    public Tile getTile() {
        return tile;
    }

}