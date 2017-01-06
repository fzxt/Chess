package com.company.piece;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Move;
import java.awt.Point;

import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Piece {
    private Team team;
    private PieceType type;
    private int value;
    private Point position;
    

    public Piece(Team team, int value, PieceType type, Point position) {
        this.team = team;
        this.value = value;
        this.type = type;
        this.position = position;
    }
    
    public Team getTeam(){
        return this.team;
    }

    public PieceType getType(){
        return this.type;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public Point getPosition(){
        return this.position;
    }
    
    public void move(Move move){
        throw new NotImplementedException();
    }
    
    public abstract ArrayList<Move> getAvailableMoves(Board board);
}
