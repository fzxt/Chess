/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.piece;

import com.company.Team;
import com.company.board.Move;
import java.util.ArrayList;

/**
 *
 * @author il13eo
 */
public class Bishop extends Piece {
    public Bishop(Team team, int value, PieceType type){
        super(team, value, type);
    }

    @Override
    public ArrayList<Move> getAvailableMoves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
