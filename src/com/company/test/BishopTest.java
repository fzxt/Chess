package com.company.test;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;
import com.company.piece.Bishop;
import com.company.piece.King;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.company.test.TestUtils.assertMovesMatch;
import static com.company.test.TestUtils.createEmptyBoard;
import static com.company.test.TestUtils.searchInLine;
import static org.junit.Assert.*;

public class BishopTest {
    @Test
    public void getAvailableMoves() throws Exception {
        Board board = createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new Bishop(Team.WHITE, middle)));
        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();

        int[][] directionOffsets = {
                {1, 1},
                {-1, -1},
                {1, -1},
                {-1, 1}
        };

        searchInLine(expected, directionOffsets, middle);
        assertEquals(expected.size(), actual.size());
        assertMovesMatch(expected, actual);
    }

}