package com.company.piece;

import com.company.GameManager;
import com.company.Team;
import com.company.board.*;
import com.company.move.*;

import java.awt.Point;

import java.util.ArrayList;

import java.util.Objects;

public abstract class Piece {
    private Team team;
    private PieceType type;
    private int value;
    private Point position;
    private int numMoves;

    /**
     * Piece object
     * @param team      team the piece is on
     * @param value     value of the piece (used for AI, scoring)
     * @param type      type of piece, rook, pawn etc
     * @param position  start position of the piece
     */
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

    /**
     * updates position of the piece, adds to movehistory
     * @param move
     */
    public void move(Move move) {
        if (type == PieceType.KING) {
            GameManager.getInstance().updateKingPosition(this);
        }

        this.numMoves++;
        MoveHistory.getInstance().addMove(move);
        this.position = move.getEnd();
    }

    public abstract int[][] positionTable();
    public abstract boolean[] positionThreats();

    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Finds the moves in line, useful for queens, rooks and bishops
     * @param board                 board to search
     * @param directionOffsets      array of x, y pairs to search (-1, -1) for bottom diag etc
     * @return
     */
    public ArrayList<Move> getMovesInLine(Board board, int[][] directionOffsets) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] offset : directionOffsets) {
            int offsetX = offset[0];
            int offsetY = offset[1];

            for (int i=1; i < 8; i++) {
                Point possiblePos = new Point(getPosition().x + (i * offsetX), getPosition().y + (i * offsetY));
                if (board.validPosition(possiblePos)) {
                    Tile possibleTile = board.getTile(possiblePos);
                    if (possibleTile.isEmpty()) {
                        moves.add(createNormalMove(possiblePos));
                    } else {
                        if (!sameTeam(possibleTile.getPiece())){
                            moves.add(createAttackMove(possiblePos));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return moves;
    }

    public boolean sameTeam(Piece piece){
        return this.getTeam() == piece.getTeam();
    }

    public Move createAttackMove(Point end){
        return new AttackMove(this.position, end);
    }

    public Move createNormalMove(Point end) {
        return new NormalMove(this.position, end);
    }

    public Move createNormalMove(Point end, MoveType moveType) {
        return new NormalMove(this.position, end, moveType);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public abstract ArrayList<Move> getAvailableMoves(Board board);

    @Override
    public String toString() {
        return getTeam() + " " + getType() + " " + getPosition();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Piece && this.team == ((Piece) obj).team && this.type == ((Piece) obj).type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    public abstract Piece copy();
}
