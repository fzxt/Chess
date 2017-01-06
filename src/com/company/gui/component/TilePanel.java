package com.company.gui.component;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel {

    private BufferedImage piece;
    private BufferedImage tile;

    public TilePanel(String tileImageIconPath) {
        try {
            tile = ImageIO.read(new File(getClass().getResource(tileImageIconPath).getFile()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public TilePanel(String tileImageIconPath, String pieceImageIconPath) {
        try {
            tile = ImageIO.read(new File(getClass().getResource(tileImageIconPath).getFile()));
            piece = ImageIO.read(new File(getClass().getResource(pieceImageIconPath).getFile()));
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

        int x = getWidth() - tile.getWidth();
        int y = getHeight() - tile.getHeight();


        if (tile != null) {
            g2d.drawImage(tile, x, y, this);
        }

        if (piece != null) {
            g2d.translate(tile.getWidth() / 2, tile.getHeight() / 2 );
            g2d.drawImage(piece, x, y, tile.getWidth()/2, tile.getHeight()/2, this);
        }
    }
}
