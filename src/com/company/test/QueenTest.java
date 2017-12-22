package com.company.test;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;
import com.company.piece.Bishop;
import com.company.piece.Queen;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static com.company.test.TestUtils.assertMovesMatch;
import static com.company.test.TestUtils.createEmptyBoard;
import static com.company.test.TestUtils.searchInLine;

public class QueenTest {

    @Test
    public void getAvailableMoves() throws Exception {
        Board board = createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new Queen(Team.WHITE, middle)));
        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();

        int[][] directionOffsets = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0},
                {1, 1},
                {-1, -1},
                {1, -1},
                {-1, 1}
        };

        searchInLine(expected, directionOffsets, middle);
        assertMovesMatch(expected, actual);
    }
}
