/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author il13eo
 */
public class King extends Piece {

    public King(Team team, Point position){
        super(team, 100, PieceType.KING, position);
    }
    
    @Override
    public ArrayList<Move> getAvailableMoves(Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
