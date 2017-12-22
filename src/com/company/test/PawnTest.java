package com.company.test;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;
import com.company.piece.Pawn;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static com.company.test.TestUtils.assertMovesMatch;
import static com.company.test.TestUtils.createEmptyBoard;
import static org.junit.Assert.assertEquals;

public class PawnTest {
    // TODO: Test for EnPassant
    @Test
    public void getAvailableMoves() throws Exception {
        Board board = createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new Pawn(Team.WHITE, middle)));
        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();
        expected.add(new NormalMove(middle, new Point(middle.x, middle.y - 1)));
        expected.add(new NormalMove(middle, new Point(middle.x, middle.y - 2)));
        assertEquals(expected.size(), actual.size());
        assertMovesMatch(expected, actual);
    }
}
