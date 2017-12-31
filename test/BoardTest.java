import com.company.Team;
import com.company.board.Board;
import com.company.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BoardTest {

    @Test
    public void isTileThreatened() {
        Board board = TestUtils.createEmptyBoard();
        Pawn white = new Pawn(Team.WHITE, new Point(4, 1));
        Pawn black = new Pawn(Team.BLACK, new Point(3, 0));
        board.getTile(new Point(4, 1)).setPiece(white);
        board.getTile(new Point(3, 0)).setPiece(black);
        boolean actualWhite = board.tileAtPointIsThreatened(Team.WHITE, new Point(4, 1));
        boolean actualBlack = board.tileAtPointIsThreatened(Team.BLACK, new Point(3, 0));
        boolean expected = true;
        assertEquals(expected, actualWhite);
        assertEquals(expected, actualBlack);
    }

}
