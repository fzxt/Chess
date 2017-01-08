package com.company.gui;

import com.company.Team;
import com.company.board.Tile;
import com.company.gui.component.TilePanel;
import com.company.piece.PieceType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;


public class GUIUtils {

    private HashMap<PieceType, String[]> map;
    private String darkTile, lightTile;

    public GUIUtils() {
        map = new HashMap<>();
        map.put(PieceType.PAWN, new String[] { getImageIconFilePath("bp"), getImageIconFilePath("wp" )} );
        map.put(PieceType.ROOK, new String[] { getImageIconFilePath("br"), getImageIconFilePath("wr") } );
        map.put(PieceType.KING, new String[] { getImageIconFilePath("bk"), getImageIconFilePath("wk") } );
        map.put(PieceType.QUEEN, new String[] { getImageIconFilePath("bq"), getImageIconFilePath("wq") } );
        map.put(PieceType.BISHOP, new String[] { getImageIconFilePath("bb"),getImageIconFilePath("wb") } );
        map.put(PieceType.KNIGHT, new String[] { getImageIconFilePath("bn"), getImageIconFilePath("wn") } );
        darkTile = "../../resources/dark.png";
        lightTile = "../../resources/light.png";
    }

    public String getImageIconFilePath(String resourceName) {
        return "../../resources/"+resourceName+".png";
    }

    public String getPieceIcon(Team team, PieceType pieceType) {
        switch (team) {
            case BLACK:
                return map.get(pieceType)[0];
            case WHITE:
                return map.get(pieceType)[1];
            default:
                return null;
        }
    }

    public String getTileIcon(Tile.TILE_COLOR tileType) {
        switch (tileType) {
            case LIGHT:
                return lightTile;
            case DARK:
                return darkTile;
            default:
                return lightTile;
        }
    }

    public Color getHighlightColor(Tile.TILE_HIGHLIGHT highlight) {
        switch (highlight) {
            case BLUE:
                return new Color(187, 222, 251);
            case YELLOW:
                return new Color(255, 241, 118);
            case RED:
                return new Color(255, 171, 145);
            case GREEN:
                return new Color(128,203,196);
            case NONE:
            default:
                return new Color(0, 0, 0);
        }
    }

    public Color getOuterHighlightColor(Tile.TILE_HIGHLIGHT highlight) {
        switch (highlight) {
            case BLUE:
                return new Color(150, 219, 239);
            case YELLOW:
                return new Color(240, 220, 100);
            case RED:
                return new Color(255, 140, 130);
            case GREEN:
                return new Color(102, 162, 156);
            case NONE:
            default:
                return new Color(0, 0, 0);
        }
    }

    public BufferedImage getHighlighted(BufferedImage tileImage, Tile.TILE_HIGHLIGHT highlight) {
        Graphics2D g2 = tileImage.createGraphics();
        Rectangle2D rect = new Rectangle2D.Double(15, 0, tileImage.getWidth(), tileImage.getHeight());
        g2.setColor(getHighlightColor(highlight));
        g2.fill(rect);
        g2.setColor(getOuterHighlightColor(highlight));
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);
        g2.dispose();
        return tileImage;
    }

    public Tile getRelevantTile(TilePanel[][] tiles, MouseEvent e) {
        Tile relevantTile = null;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == e.getSource()) {
                    relevantTile = tiles[i][j].getTile();
                    break;
                }
            }
        }

        return relevantTile;
    }

}
