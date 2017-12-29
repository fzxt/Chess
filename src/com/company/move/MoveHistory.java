package com.company.move;

import java.util.Stack;

public class MoveHistory {
    private static MoveHistory instance = null;
    private  Stack<Move> moveList = new Stack<>();

    public static MoveHistory getInstance() {
        if (instance == null){
            instance = new MoveHistory();
        }

        return instance;
    }

    public void addMove(Move move){
        moveList.add(move);
    }

    public Stack<Move> getMoveHistory() {
        return moveList;
    }

    public Move popLastMove() {
        return moveList.pop();
    }
}
