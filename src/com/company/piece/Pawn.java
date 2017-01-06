package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import com.company.board.MoveType;
import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Team team, int value, PieceType type, Point position) {
        super(team, value, type, position);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        Point currPos = super.getPosition();

        for (int i = 1; i <= 2; i++) {
            Point newPos = new Point(currPos.x, currPos.y + i);
            if (board.getTile(newPos).isEmpty()) {
                moves.add(new Move(currPos, newPos, MoveType.NORMAL));
            }
            break;
        }

        for (int i = 1; i <= 2; i++) {
            int val = i == 1 ? i : -1;
            Point newPos = new Point(currPos.x + val, currPos.y + 1); 
            if (!board.getTile(newPos).isEmpty()){
                if (board.getTile(newPos).getPiece().getTeam() != super.getTeam()){
                    moves.add(new Move(currPos, newPos, MoveType.ATTACK));
                }
            }
        }
        throw new UnsupportedOperationException("En passant not implemented, board bounds not implemented");
    }
}
