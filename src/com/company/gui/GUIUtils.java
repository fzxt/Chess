package com.company.gui;

import com.company.Team;
import com.company.board.Tile;
import com.company.gui.component.TilePanel;
import com.company.piece.PieceType;

import java.util.HashMap;

public class GUIUtils {

    private HashMap<PieceType, String[]> map;

    public static enum TILE_TYPE {
        LIGHT,
        DARK
    }

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

    private String getImageIconFilePath(String resourceName) {
        return "../../resources/"+resourceName+".png";
    }

    String getPieceIcon(Team team, PieceType pieceType) {
        switch (team) {
            case BLACK:
                return map.get(pieceType)[0];
            case WHITE:
                return map.get(pieceType)[1];
            default:
                return null;
        }
    }

    String getTileIcon(TILE_TYPE tileType) {
        switch (tileType) {
            case LIGHT:
                return lightTile;
            case DARK:
                return darkTile;
            default:
                return lightTile;
        }
    }
}
