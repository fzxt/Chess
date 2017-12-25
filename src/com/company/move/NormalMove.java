package com.company.move;

import com.company.GameManager;
import com.company.board.Tile;
import com.company.piece.Piece;

import java.awt.*;

public class NormalMove extends Move {

    public NormalMove(Point start, Point end) {
        super(start, end, MoveType.NORMAL);
    }

    public NormalMove(Point start, Point end, MoveType type) {
        super(start, end, type);
    }

    @Override
    public Tile.TILE_HIGHLIGHT getTileHighlight() {
        return Tile.TILE_HIGHLIGHT.BLUE;
    }

    @Override
    public void undo(GameManager gm) {
        Tile end = gm.getTile(getEnd());
        Piece movedPiece = end.getPiece();
        movedPiece.setPosition(start);
        gm.setTile(new Point(getEnd()), new Tile(getEnd()));
        gm.setTile(new Point(start), new Tile(movedPiece));
    }
}
