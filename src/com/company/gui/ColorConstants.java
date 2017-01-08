package com.company.gui;

import com.company.board.Tile;

import java.awt.*;

import static com.company.board.Tile.TILE_TYPE.*;

/**
 * Useful color constants.
 */
public class ColorConstants {
    private static final Color lightBlueFill =  new Color(187, 222, 251);
    private static final Color darkBlueOuter = new Color(150, 219, 239);
    private static final Color lightRedFill = new Color(255, 171, 145);
    private static final Color darkRedOuter = new Color(255, 140, 130);
    private static final Color lightGreenFill = new Color(128,203,196);
    private static final Color darkGreenOuter = new Color(102, 162, 156);
    private static final Color lightYellowFill = new Color(255, 241, 118);
    private static final Color darkYellowOuter = new Color(240, 220, 100);

    private static final Color darkTileColor = new Color(145, 103, 69);
    private static final Color lightTileColor = new Color(230, 179, 136);

    static final Color[] normalColorsLightTile = new Color[] {
            getSimulatedAlphaColor(LIGHT, lightBlueFill),
            getSimulatedAlphaColor(LIGHT, darkBlueOuter)};

    static final Color[] normalColorsDarkTile = new Color[] {
            getSimulatedAlphaColor(DARK, lightBlueFill),
            getSimulatedAlphaColor(DARK, darkBlueOuter)};

    static final Color[] specialColorsLightTile = new Color[] {
            getSimulatedAlphaColor(LIGHT, lightYellowFill),
            getSimulatedAlphaColor(LIGHT, darkYellowOuter)};

    static final Color[] specialColorsDarkTile = new Color[] {
            getSimulatedAlphaColor(DARK, lightYellowFill),
            getSimulatedAlphaColor(DARK, darkYellowOuter)};

    static final Color[] attackColorsLightTile = new Color[] {
            getSimulatedAlphaColor(LIGHT, lightRedFill),
            getSimulatedAlphaColor(LIGHT, darkRedOuter)};

    static final Color[] attackColorsDarkTile = new Color[] {
            getSimulatedAlphaColor(DARK, lightRedFill),
            getSimulatedAlphaColor(DARK, darkRedOuter)};

    static final Color[] selectedColorsLightTile = new Color[] {
            getSimulatedAlphaColor(LIGHT, lightGreenFill),
            getSimulatedAlphaColor(LIGHT, darkGreenOuter)};

    static final Color[] selectedColorsDarkTile = new Color[] {
            getSimulatedAlphaColor(DARK, lightGreenFill),
            getSimulatedAlphaColor(DARK, darkGreenOuter)};

    /**
     * Helper method to get the simulated alpha color.
     * It adds the colors of the tile and the desired color together based on a factor, in order to simulate alpha.
     * @param tileType          Tile type, either LIGHT or DARK.
     * @param desiredColor      Desired color.
     * @return                  New simulated alpha color.
     */
    private static Color getSimulatedAlphaColor(Tile.TILE_TYPE tileType, Color desiredColor) {
        float factor = 160 / 255f;
        Color tileColor = tileType == LIGHT ? lightTileColor : darkTileColor;
        int red = (int) (tileColor.getRed() * (1 - factor) + desiredColor.getRed() * factor);
        int green = (int) (tileColor.getGreen() * (1 - factor) + desiredColor.getGreen() * factor);
        int blue = (int) (tileColor.getBlue() * (1 - factor) + desiredColor.getBlue() * factor);
        return new Color(red, green, blue);
    }
}