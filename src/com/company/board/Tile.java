package com.company.board;

import com.company.Team;
import com.company.piece.Piece;

import java.awt.Point;

public class Tile {

    public enum TILE_COLOR {
        LIGHT,
        DARK,
    }

    public enum TILE_HIGHLIGHT {
        NONE,
        BLUE,
        YELLOW,
        RED,
        GREEN
    }

    private final Point position;
    private TILE_COLOR color;
    private TILE_HIGHLIGHT highlight;


    private Piece piece;

    public Tile(Point position) {
        this.piece = null;
        this.position = position;
        this.color = (position.x % 2 == 0 && position.y % 2 == 0
                || position.x % 2 == 1 && position.y % 2 == 1) ? TILE_COLOR.LIGHT : TILE_COLOR.DARK;
        this.highlight = TILE_HIGHLIGHT.NONE;
    }

    public Tile(Piece piece) {
        this.piece = piece;
        this.position = piece.getPosition();
        this.color = (position.x % 2 == 0 && position.y % 2 == 0
                || position.x % 2 == 1 && position.y % 2 == 1) ? TILE_COLOR.LIGHT : TILE_COLOR.DARK;
        this.highlight = TILE_HIGHLIGHT.NONE;
    }

    public TILE_COLOR getColor() {
        return color;
    }

    public Point getPosition() {
        return position;
    }

    public Point getPiecePosition() {
        return this.piece.getPosition();
    }

    public boolean isEmpty() {
        return this.piece == null;
    }
    
    public Piece getPiece() {
        return this.piece;
    }

    public TILE_HIGHLIGHT getHighlight() {
        return highlight;
    }

    public void setHighlight(TILE_HIGHLIGHT highlight) {
        this.highlight = highlight;
    }

    public boolean isHiglighted() {
        return this.highlight != TILE_HIGHLIGHT.NONE;
    }

    @Override
    public String toString() {
        String team = " ";
        if (!isEmpty()){
            team = this.piece.getTeam() == Team.BLACK ? "b" : "w";
        }
        return this.piece == null ? team + "NULL" : team + this.piece.getType().toString();
    }
}
