package com.company.ai;

import com.company.Team;
import com.company.board.Board;
import com.company.board.Tile;
import com.company.move.Move;
import com.company.piece.Piece;

import java.awt.*;
import java.util.ArrayList;

public class AI {

    private final int maxDepth;
    private Move bestMove;

    public AI(int maxDepth) {
        this.maxDepth = maxDepth;
        this.bestMove = null;
    }

    public Move bestMove(Board board) {
        alphaBetaPruning(board, Team.BLACK, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
        return bestMove;
    }

    private double alphaBetaPruning(Board board, Team team, double alpha, double beta, int depth) {
        if (depth++ == maxDepth) {
            return score(team, board, depth);
        }

        boolean maximize = team == Team.BLACK;

        ArrayList<Move> moves = getAllPossibleMoves(board, team);

        if (maximize) {
            Move localBestMove = null;
            for (Move move : moves) {
                Board copy = new Board(board);
                move.handleMove(copy);
                double score = alphaBetaPruning(copy, switchTeam(team), alpha, beta, depth);
                move.undo(copy);
                if (score > alpha) {
                    alpha = score;
                    localBestMove = move;
                }

                if (beta <= alpha) {
                    break;
                }
            }

            if (localBestMove != null)
                bestMove = localBestMove;

            return alpha;
        } else {
            for (Move move : moves) {
                Board copy = new Board(board);
                move.handleMove(copy);
                double score = alphaBetaPruning(copy, switchTeam(team), alpha, beta, depth);
                move.undo(copy);

                if (score < beta) {
                    beta = score;
                }

                if (beta <= alpha) {
                    break;
                }
            }


            return beta;
        }
    }

    private ArrayList<Move> getAllPossibleMoves(Board board, Team team) {
        ArrayList<Move> results = new ArrayList<>();

        for (Tile[] tiles : board.getBoard()) {
            for (Tile tile : tiles) {
                if (!tile.isEmpty()) {
                    Piece piece = tile.getPiece();
                    if (piece.getTeam() == team) {
                        results.addAll(piece.getAvailableMoves(board));
                    }
                }
            }
        }

        return results;
    }

    private double score(Team team, Board board, int depth) {
        int score = 0;

        for (Tile[] tiles : board.getBoard()) {
            for (Tile tile : tiles) {
                if (!tile.isEmpty()) {
                    Piece piece = tile.getPiece();
                    if (piece.getTeam() == team) {
                        score += piece.getValue();
                        score += valueOfPosition(piece);
                        score -= depth;
                    } else {
                        score -= piece.getValue();
                        score -= valueOfPosition(piece);
                        score += depth;
                    }
                }
            }
        }

        return score;
    }

    private int valueOfPosition(Piece piece) {
        Point position = piece.getPosition();
        int x = (int) position.getX();
        int y = (int) ((piece.getTeam() == Team.WHITE) ? (position.getY()) : (7 - position.getY()));
        return piece.positionTable()[y][x];
    }

    private Team switchTeam(Team team) {
        return team == Team.WHITE ? Team.BLACK : Team.WHITE;
    }

}
