package com.company;

import com.company.board.Board;
import com.company.gui.BoardPresenter;
import com.company.gui.BoardView;
import com.company.piece.Pawn;
import com.company.piece.Piece;
import com.company.piece.PieceType;

import javax.swing.*;
import java.awt.Point;

public class Game {

    public Game() {
        // Initialization logic here
        new BoardPresenter(new BoardView(), new Board());
    }

    public static void main(String[] args) {
        new Game();
    }
}