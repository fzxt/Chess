package com.company.move;

import java.util.LinkedList;

public class MoveHistory {
    private static MoveHistory instance = null;
    private static LinkedList<Move> moveList = new LinkedList<>();
    protected MoveHistory(){ }

    public static MoveHistory getInstance() {
        if (instance == null){
            instance = new MoveHistory();
        }
        return instance;
    }

    public static void addMove(Move move){
        moveList.add(move);
    }

    public static LinkedList<Move> getMoveList(){
        return moveList;
    }

    public static Move getLastMove(){
        return moveList.getLast();
    }
}
