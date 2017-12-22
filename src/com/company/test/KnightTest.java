package com.company.test;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;
import com.company.piece.Bishop;
import com.company.piece.Knight;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static com.company.test.TestUtils.assertMovesMatch;
import static com.company.test.TestUtils.createEmptyBoard;
import static org.junit.Assert.*;

public class KnightTest {

    Point middle = new Point(4, 4);

    @Test
    public void getAvailableMoves() {
        Point[] possibleMoves = {
                generatePos(-1, -2),
                generatePos(1, -2),
                generatePos(-1, 2),
                generatePos(1, 2),
                generatePos(-2, -1),
                generatePos(2, -1),
                generatePos(-2, 1),
                generatePos(2, 1),
        };

        Board board = createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new Knight(Team.WHITE, middle)));

        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();

        for (Point possibleMove : possibleMoves) {
            expected.add(new NormalMove(middle, possibleMove));
        }

        assertEquals(expected.size(), actual.size());

        assertMovesMatch(expected, actual);
    }

    private Point generatePos(int x, int y) {
        return new Point(middle.x + x, middle.y + y);
    }
}