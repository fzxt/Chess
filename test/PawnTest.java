import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;
import com.company.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PawnTest {
    // TODO: Test for EnPassant
    @Test
    public void getAvailableMoves() throws Exception {
        Board board = TestUtils.createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new Pawn(Team.WHITE, middle)));
        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();
        expected.add(new NormalMove(middle, new Point(middle.x, middle.y - 1)));
        expected.add(new NormalMove(middle, new Point(middle.x, middle.y - 2)));
        assertEquals(expected.size(), actual.size());
        TestUtils.assertMovesMatch(expected, actual);
    }

    @Test
    public void pawnPromotion() {
        Board board = TestUtils.createEmptyBoard();
        Point secondRowVeryRight = new Point(7 ,1);
        Point secondLastRowVeryLeft = new Point(0 ,6);

        board.setTile(secondRowVeryRight, new Tile(new Pawn(Team.WHITE, secondRowVeryRight)));
        board.setTile(secondLastRowVeryLeft, new Tile(new Pawn(Team.BLACK, secondLastRowVeryLeft)));

        ArrayList<Move> actualWhite = board.getTile(secondRowVeryRight).getPiece().getAvailableMoves(board);
        ArrayList<Move> expectedWhite = new ArrayList<>();

        ArrayList<Move> actualBlack = board.getTile(secondLastRowVeryLeft).getPiece().getAvailableMoves(board);
        ArrayList<Move> expectedBlack = new ArrayList<>();

        expectedWhite.add(new NormalMove(secondRowVeryRight, new Point(7, 0)));
        expectedBlack.add(new NormalMove(secondLastRowVeryLeft, new Point(0, 7)));

        assertEquals(expectedWhite.size(), actualWhite.size());
        assertEquals(expectedBlack.size(), actualBlack.size());

        TestUtils.assertMovesMatch(expectedWhite, actualWhite);
        TestUtils.assertMovesMatch(expectedBlack, actualBlack);
    }
}
