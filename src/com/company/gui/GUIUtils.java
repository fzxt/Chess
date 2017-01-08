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

import static com.company.board.Tile.TILE_TYPE.*;
import static com.company.gui.ColorConstants.*;


public class GUIUtils {

    private HashMap<PieceType, String[]> map;
    private String darkTileImagePath, lightTileImagePath;


    public GUIUtils() {
        map = new HashMap<>();
        map.put(PieceType.PAWN, new String[] { getImageIconFilePath("bp"), getImageIconFilePath("wp" )} );
        map.put(PieceType.ROOK, new String[] { getImageIconFilePath("br"), getImageIconFilePath("wr") } );
        map.put(PieceType.KING, new String[] { getImageIconFilePath("bk"), getImageIconFilePath("wk") } );
        map.put(PieceType.QUEEN, new String[] { getImageIconFilePath("bq"), getImageIconFilePath("wq") } );
        map.put(PieceType.BISHOP, new String[] { getImageIconFilePath("bb"),getImageIconFilePath("wb") } );
        map.put(PieceType.KNIGHT, new String[] { getImageIconFilePath("bn"), getImageIconFilePath("wn") } );


        darkTileImagePath = "../../resources/dark.png";
        lightTileImagePath = "../../resources/light.png";
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

    public String getTileIconPath(Tile.TILE_TYPE tileType) {
        switch (tileType) {
            case LIGHT:
                return lightTileImagePath;
            case DARK:
                return darkTileImagePath;
            default:
                return lightTileImagePath;
        }
    }

    /**
     * Get array of highlight colors with simulated alpha.
     * @param tileType     Tile type, either LIGHT or DARK.
     * @param highlight    Highlight of the tile.
     * @return             Array of colors with 2 colors, 0th index is inner fill color, 1st index is border color.
     */
    private Color[] getHighlightColors(Tile.TILE_TYPE tileType, Tile.TILE_HIGHLIGHT highlight) {
        switch (highlight) {
            case BLUE:
                return tileType == LIGHT ? normalColorsLightTile : normalColorsDarkTile;
            case YELLOW:
                return tileType == LIGHT ? specialColorsLightTile : specialColorsDarkTile;
            case RED:
                return tileType == LIGHT ? attackColorsLightTile : attackColorsDarkTile;
            case GREEN:
                return tileType == LIGHT ? selectedColorsLightTile : selectedColorsDarkTile;
            case NONE:
            default:
                return new Color[]{ new Color(0, 0, 0), new Color(0, 0, 0)};
        }
    }

    /**
     * Get highlighted image of tile to draw. Alters a provided image tile.
     * @param tileType     Type of tile.
     * @param tileImage    Image of tile to alter.
     * @param highlight    Color to highlight tile.
     * @return             Image of a highlighted tile.
     */
    public BufferedImage getHighlighted(Tile.TILE_TYPE tileType, BufferedImage tileImage, Tile.TILE_HIGHLIGHT highlight) {
        Graphics2D g2 = tileImage.createGraphics();
        Rectangle2D rect = new Rectangle2D.Double(15, 0, tileImage.getWidth(), tileImage.getHeight());
        Color[] highlightColors = getHighlightColors(tileType, highlight);
        g2.setColor(highlightColors[0]);
        g2.fill(rect);
        g2.setColor(highlightColors[1]);
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);
        g2.dispose();
        return tileImage;
    }

    /**
     * Gets the TilePanel component associated with the mouse event.
     * @param tiles     Array of TilePanels to search from.
     * @param e         Mouse event (click, hover, drag, etc).
     * @return          Tile that was clicked, hovered, dragged upon etc.
     */
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
