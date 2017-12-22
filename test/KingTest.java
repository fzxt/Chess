import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;
import com.company.piece.King;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class KingTest {

    // TODO: Test castling

    @Test
    public void getAvailableMoves() throws Exception {
        Board board = TestUtils.createEmptyBoard();
        Point middle = new Point(4, 4);
        board.setTile(middle, new Tile(new King(Team.WHITE, middle)));

        ArrayList<Move> actual = board.getTile(middle).getPiece().getAvailableMoves(board);
        ArrayList<Move> expected = new ArrayList<>();

        // The king is allowed to move one tile over in all 8 directions
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Point possiblePos = new Point(middle.x + i, middle.y + j);
                if (!possiblePos.equals(middle))
                    expected.add(new NormalMove(middle, possiblePos));
            }
        }

        assertEquals(expected.size(), actual.size());
        TestUtils.assertMovesMatch(expected, actual);
    }
}