package com.company.test;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.piece.Queen;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static com.company.test.TestUtils.assertMovesMatch;
import static com.company.test.TestUtils.createEmptyBoard;
import static com.company.test.TestUtils.searchInLine;
import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void getAvailableMoves() throws Exception {
        Board board = createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new Queen(Team.WHITE, middle)));
        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();

        int[][] directionOffsets = {
                {0, 1}, // Up
                {0, -1}, // Down
                {1, 0}, // Left
                {-1, 0} // Right
        };


        searchInLine(expected, directionOffsets, middle);
        assertEquals(expected.size(), actual.size());
        assertMovesMatch(expected, actual);
    }
}