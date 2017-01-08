package com.company.piece;

import com.company.Team;
import com.company.board.*;

import java.awt.Point;

import java.util.ArrayList;

import java.util.Objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Piece {
    private Team team;
    private PieceType type;
    private int value;
    private Point position;
    private int numMoves;

    public Piece(Team team, int value, PieceType type, Point position) {
        this.team = team;
        this.value = value;
        this.type = type;
        this.position = position;
        this.numMoves = 0;
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

    public void move(Move move) {
        this.numMoves++;
        MoveHistory.addMove(move);
        throw new NotImplementedException();
    }

    public int getNumMoves() {
        return numMoves;
    }

    public ArrayList<Move> getMovesInLine(Board board, int[][] directionOffsets){
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] offset : directionOffsets) {
            int offsetX = offset[0];
            int offsetY = offset[1];

            for (int i=1; i < 8; i++){
                Point possiblePos = new Point(getPosition().x + (i * offsetX), getPosition().y + (i * offsetY));
                if (board.validPosition(possiblePos)){
                    Tile possibleTile = board.getTile(possiblePos);
                    if (possibleTile.isEmpty()){
                        moves.add(createMove(possiblePos));
                    }else {
                        if (!sameTeam(possibleTile.getPiece())){
                            moves.add(createMove(possiblePos, MoveType.ATTACK));
                        }
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        return moves;
    }


    public boolean sameTeam(Piece piece){
        return this.getTeam() == piece.getTeam();
    }

    public Move createMove(Point end, MoveType moveType){
        return new Move(this.position, end, moveType);
    }

    public Move createMove(Point end){
        return new Move(this.position, end, MoveType.NORMAL);
    }

    public abstract ArrayList<Move> getAvailableMoves(Board board);

    @Override
    public String toString() {
        return getTeam() + " " + getType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Piece && this.team == ((Piece) obj).team && this.type == ((Piece) obj).type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}
