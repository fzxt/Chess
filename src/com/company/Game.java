package com.company;

import com.company.board.Board;
import com.company.piece.Pawn;
import com.company.piece.Piece;
import com.company.piece.PieceType;
import java.awt.Point;

public class Game {

    public Game() {
        // Initialization logic here
        Piece p = new Pawn(Team.BLACK, new Point(1, 1));
        p.getAvailableMoves(new Board());
    }

    public static void main(String[] args) {
        new Game();
    }
}