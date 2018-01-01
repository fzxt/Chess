import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.MoveType;
import com.company.move.NormalMove;
import com.company.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PawnTest {
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

    @Test
    public void enPassant() {
        Board board = TestUtils.createEmptyBoard();
        Point whiteStartingPoint = new Point(5, 6);
        Point blackStartingPoint = new Point(4, 1);

        Pawn white = new Pawn(Team.WHITE, whiteStartingPoint);
        Pawn black = new Pawn(Team.BLACK, blackStartingPoint);

        board.setTile(whiteStartingPoint, new Tile(white));
        board.setTile(blackStartingPoint, new Tile(black));

        // For enpassant to happen, the opponents last move needs to be a double move, where our last move was a single move.
        // And the pawns need to be on either side.
        NormalMove doubleMoveWhite = new NormalMove(whiteStartingPoint, new Point(5, 4), MoveType.NORMAL_DOUBLE);
        white.move(doubleMoveWhite);
        board.handleMove(doubleMoveWhite);

        NormalMove singleMoveWhite = new NormalMove(new Point(5, 4), new Point(5, 3));
        white.move(singleMoveWhite);
        board.handleMove(singleMoveWhite);

        NormalMove doubleMove = new NormalMove(blackStartingPoint, new Point(4, 3), MoveType.NORMAL_DOUBLE);
        black.move(doubleMove);
        board.handleMove(doubleMove);

        // Our assertion will simply be to check if a enpassant move is available for the white pawn.
        boolean containsEnpassant = false;
        ArrayList<Move> moves = white.getAvailableMoves(board);
        for (Move move : moves) {
            if (move.getType() == MoveType.ENPASSANT) {
                containsEnpassant = true;
            }
        }

        assertEquals(true, containsEnpassant);
    }
}
