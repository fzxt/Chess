import com.company.Player;
import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.move.NormalMove;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    static Board createEmptyBoard() {
        Board board = new Board(new Player(Team.WHITE), new Player(Team.BLACK));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Point point = new Point(i, j);
                board.setTile(point, new Tile(point));
            }
        }

        return board;
    }

    static void searchInLine(ArrayList<Move> expected, int[][] directionOffsets, Point pos) {
        for (int[] offset : directionOffsets) {
            int offsetX = offset[0];
            int offsetY = offset[1];
            for (int i = 1; i < 8; i++) {
                Point possiblePos = new Point(pos.x + (i * offsetX), pos.y + (i * offsetY));
                if (validPosition(possiblePos))
                    expected.add(new NormalMove(pos, possiblePos));
            }
        }
    }

    private static boolean validPosition(Point position) {
        return (position.x >= 0 && position.x <= 7 && position.y >= 0 && position.y <= 7);
    }

    static void assertMovesMatch(ArrayList<Move> expected, ArrayList<Move> actual) {
        for (int i = 0; i < expected.size(); i++) {
            Move actualMove = actual.get(i);
            Move expectedMove = expected.get(i);
            assertEquals(actualMove.toString(), expectedMove.toString());
        }
    }

}
