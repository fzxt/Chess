import com.company.GameManager;
import com.company.Team;
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
    private GameManager gm;

    @BeforeEach
    public void setUp() {
        gm = new GameManager(TestUtils.createEmptyBoard());
        kingStarting = new Point(4, 7);
        rookStartingRight = new Point(7, 7);
        rookStartingLeft = new Point(0, 7);

        gm.setTile(kingStarting, new Tile(new King(Team.WHITE, kingStarting)));
        gm.setTile(rookStartingRight, new Tile(new Rook(Team.WHITE, rookStartingRight)));
        gm.setTile(rookStartingLeft, new Tile(new Rook(Team.WHITE, rookStartingLeft)));

        ArrayList<Move> kingMoves = gm.getTile(kingStarting).getPiece().getAvailableMoves(gm.getBoard());
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
        Tile leftMoveTarget = gm.getTile(leftMove.getEnd());
        leftMove.handleMove(gm, leftMoveTarget);
        leftMoveTarget = gm.getTile(leftMove.getEnd());
        assertEquals(false, leftMoveTarget.isEmpty());
        assertEquals(leftMoveTarget.getPiece().getType(), PieceType.KING);
        assertEquals(gm.getTile(new Point(leftMove.getEnd().x + 1, leftMove.getEnd().y)).getPiece().getType(), PieceType.ROOK);
    }

    @Test
    public void handleMoveRight() throws Exception {
        CastleMove rightMove = castleMoves.get(1);
        Tile rightMoveTarget = gm.getTile(rightMove.getEnd());
        rightMove.handleMove(gm, rightMoveTarget);
        rightMoveTarget = gm.getTile(rightMove.getEnd());
        assertEquals(false, rightMoveTarget.isEmpty());
        assertEquals(rightMoveTarget.getPiece().getType(), PieceType.KING);
        assertEquals(gm.getTile(new Point(rightMove.getEnd().x - 1, rightMove.getEnd().y)).getPiece().getType(), PieceType.ROOK);
    }
}