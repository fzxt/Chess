import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.piece.Bishop;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {
    @Test
    public void getAvailableMoves() throws Exception {
        Board board = TestUtils.createEmptyBoard();
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

        TestUtils.searchInLine(expected, directionOffsets, middle);
        assertEquals(expected.size(), actual.size());
        TestUtils.assertMovesMatch(expected, actual);
    }
}