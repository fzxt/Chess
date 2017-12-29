import com.company.GameManager;
import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.CastleMove;
import com.company.move.Move;
import com.company.move.MoveType;
import com.company.piece.King;
import com.company.piece.PieceType;
import com.company.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CastleMoveTest {
    private Point rookStartingRight;
    private Point kingStarting;
    private Point rookStartingLeft;
    private ArrayList<CastleMove> castleMoves;
    private Board board;

    @BeforeEach
    public void setUp() {
        board = TestUtils.createEmptyBoard();
        kingStarting = new Point(4, 7);
        rookStartingRight = new Point(7, 7);
        rookStartingLeft = new Point(0, 7);

        board.setTile(kingStarting, new Tile(new King(Team.WHITE, kingStarting)));
        board.setTile(rookStartingRight, new Tile(new Rook(Team.WHITE, rookStartingRight)));
        board.setTile(rookStartingLeft, new Tile(new Rook(Team.WHITE, rookStartingLeft)));

        ArrayList<Move> kingMoves = board.getTile(kingStarting).getPiece().getAvailableMoves(board);
        castleMoves = new ArrayList<>();

        for (Move move : kingMoves) {
            if (move.getType() == MoveType.CASTLE) {
                castleMoves.add(new CastleMove(move.start, move.getEnd()));
            }
        }
    }

    @Test
    public void handleMoveLeft() throws Exception {
        CastleMove leftMove = castleMoves.get(0);
        Tile leftMoveTarget;
        leftMove.handleMove(board);
        leftMoveTarget = board.getTile(leftMove.getEnd());
        assertEquals(false, leftMoveTarget.isEmpty());
        assertEquals(leftMoveTarget.getPiece().getType(), PieceType.KING);
        assertEquals(board.getTile(new Point(leftMove.getEnd().x + 1, leftMove.getEnd().y)).getPiece().getType(), PieceType.ROOK);
    }

    @Test
    public void handleMoveRight() throws Exception {
        CastleMove rightMove = castleMoves.get(1);
        rightMove.handleMove(board);
        Tile rightMoveTarget = board.getTile(rightMove.getEnd());
        assertEquals(false, rightMoveTarget.isEmpty());
        assertEquals(rightMoveTarget.getPiece().getType(), PieceType.KING);
        assertEquals(board.getTile(new Point(rightMove.getEnd().x - 1, rightMove.getEnd().y)).getPiece().getType(), PieceType.ROOK);
    }
}