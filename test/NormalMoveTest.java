import com.company.board.Board;
import com.company.move.NormalMove;
import com.company.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.company.Team.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalMoveTest {
    @Test
    public void undo() {
        Board board = TestUtils.createEmptyBoard();
        // We'll test with pawns
        Point start = new Point(4, 3);
        Pawn pawn = new Pawn(WHITE, new Point(4, 3));
        board.getTile(new Point(4, 3)).setPiece(pawn);

        NormalMove single = new NormalMove(pawn.getPosition(), new Point(4, 2));
        single.handleMove(board);
        NormalMove dbl = new NormalMove(pawn.getPosition(), new Point(4, 0));
        dbl.handleMove(board);

        dbl.undo(board);
        single.undo(board);

        // Expected to be at the start, after undoing the last two moves
        assertEquals(start, pawn.getPosition());
    }
}
