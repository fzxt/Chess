import com.company.Team;
import com.company.board.Board;
import com.company.move.EnpassantMove;
import com.company.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EnpassantMoveTest {
    @Test
    void handleMove() {
        Board board = TestUtils.createEmptyBoard();
        Pawn white = new Pawn(Team.WHITE, new Point(5, 3));
        Pawn black = new Pawn(Team.BLACK, new Point(4, 3));
        board.getTile(new Point(5, 3)).setPiece(white);
        board.getTile(new Point(4, 3)).setPiece(black);
        EnpassantMove move = new EnpassantMove(new Point(5, 3), new Point(4 ,2));
        move.handleMove(board);
        assertEquals(white.getPosition().x, 4);
        assertEquals(white.getPosition().y, 2);
        assertEquals(board.getTile(new Point(4, 3)).isEmpty(), true);
    }
}