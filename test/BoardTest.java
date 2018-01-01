import com.company.Team;
import com.company.board.Board;
import com.company.piece.King;
import com.company.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BoardTest {

    @Test
    public void isTileThreatenedEmptyBoard() {
        Board board = TestUtils.createEmptyBoard();
        Point whitePawnPosition = new Point(4, 1);
        Point blackPawnPosition = new Point(3, 0);
        Pawn white = new Pawn(Team.WHITE, whitePawnPosition);
        Pawn black = new Pawn(Team.BLACK, blackPawnPosition);
        board.getTile(whitePawnPosition).setPiece(white);
        board.getTile(blackPawnPosition).setPiece(black);
        boolean actualWhite = board.tileAtPointIsThreatened(Team.WHITE, whitePawnPosition);
        boolean actualBlack = board.tileAtPointIsThreatened(Team.BLACK, blackPawnPosition);
        assertEquals(true, actualWhite);
        assertEquals(true, actualBlack);
    }

    @Test
    public void isTileThreatenedShouldBeFalseOnEmptyBoard() {
        Board board = TestUtils.createEmptyBoard();
        King king = new King(Team.WHITE, new Point(7, 5));
        Pawn pawn = new Pawn(Team.BLACK, new Point(0, 0));
        board.getTile(new Point(7, 5)).setPiece(king);
        board.getTile(new Point(0, 0)).setPiece(pawn);
        boolean actual = board.tileAtPointIsThreatened(Team.WHITE, new Point(7, 5));
        assertEquals(false, actual);
    }

    @Test
    public void isTileThreatenedShouldBeFalseOnRegularBoard() {
        Board board = TestUtils.createRegularBoard();
        boolean actual = board.tileAtPointIsThreatened(Team.WHITE, new Point(7, 4));
        assertEquals(false, actual);
    }

}
