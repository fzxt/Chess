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

    /**
     * Gets the best move via Alpha Beta
     * @param board     Initial board
     * @return          Best move the AI can make
     */
    public Move bestMove(Board board) {
        alphaBetaPruning(board, Team.BLACK, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
        return bestMove;
    }

    /**
     * Recursive method for alpha beta pruning
     * @param board     Board
     * @param team      Team AI identifies with
     * @param alpha     Alpha value (initially -inf)
     * @param beta      Beta value (initially +inf)
     * @param depth     Current depth (initially 0)
     * @return          Alpha when maximizing, beta when minimizing
     */
    private double alphaBetaPruning(Board board, Team team, double alpha, double beta, int depth) {
        boolean maximize = team == Team.BLACK;

        if (depth++ == maxDepth) {
            return score(maximize, board);
        }
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

    /**
     * Gets all possible moves that can be made on the board for a team
     * @param board    board to make moves
     * @param team     team to get moves for
     * @return         List of available moves
     */
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

    /**
     * Evaluates the board
     * @param maximize      whether to add or subtract from score
     * @param board         board to evaluate
     * @return
     */
    private double score(boolean maximize, Board board) {
        int score = 0;

        for (Tile[] tiles : board.getBoard()) {
            for (Tile tile : tiles) {
                if (!tile.isEmpty()) {
                    Piece piece = tile.getPiece();
                    Point position = piece.getPosition();
                    int x = (int) piece.getPosition().getX();
                    int y = (int) ((piece.getTeam() == Team.WHITE) ? (position.getY()) : (7 - position.getY()));

                    if (maximize) {
                        score += piece.getValue() + piece.positionTable()[y][x];
                    } else {
                        score -= piece.getValue() + piece.positionTable()[y][x];
                    }
                }
            }
        }

        return score;
    }

    private Team switchTeam(Team team) {
        return team == Team.WHITE ? Team.BLACK : Team.WHITE;
    }

}
