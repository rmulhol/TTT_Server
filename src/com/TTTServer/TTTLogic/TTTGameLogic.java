package com.TTTServer.TTTLogic;

import tic_tac_toe.ai_player;
import tic_tac_toe.board;
import tic_tac_toe.rules;

import java.util.ArrayList;
import java.util.HashMap;

public class TTTGameLogic {

    public static TTTBoardState getNextState(TTTBoardState boardState) {
        ArrayList<String> board = boardState.getBoard();
        String player1Id = boardState.getPlayer1Id();
        String player1Mv = boardState.getPlayer1Mv();
        String player2Id = boardState.getPlayer2Id();
        String player2Mv = boardState.getPlayer2Mv();

        HashMap<String, String> player1 = new HashMap<String, String>();
        player1.put("id", player1Id);
        player1.put("move", player1Mv);

        HashMap<String, String> player2 = new HashMap<String, String>();
        player2.put("id", player2Id);
        player2.put("move", player2Mv);

        if (!boardState.hasMove()) {
            return makeCurrentPlayerMove(player1, player2, board);
        } else {
            HashMap<String, String> currentPlayer = currentPlayer(board, player1, player2);
            ArrayList<String> newBoard = makeHumanMove(currentPlayer.get("move"), boardState.getMove(), board);
            if (gameOver(newBoard, player1Mv, player2Mv)) {
                TTTBoardState outputParams = new TTTBoardState(player1Mv, player2Mv, player1Id, player2Id, newBoard);
                outputParams.setGameOver();
                return outputParams;
            } else {
                return makeCurrentPlayerMove(player1, player2, newBoard);
            }
        }
    }

    private static TTTBoardState makeCurrentPlayerMove(HashMap<String, String> player1, HashMap<String, String> player2, ArrayList<String> currentBoard) {
        String player1Id = player1.get("id");
        String player1Mv = player1.get("move");
        String player2Id = player2.get("id");
        String player2Mv = player2.get("move");

        HashMap<String, String> currentPlayer = currentPlayer(currentBoard, player1, player2);

        if (humanPlayer(currentPlayer)) {
            return new TTTBoardState(player1Mv, player2Mv, player1Id, player2Id, currentBoard);
        } else {
            currentBoard = makeAiMove(currentPlayer.get("move"), currentBoard);
            if (gameOver(currentBoard, player1Mv, player2Mv)) {
                TTTBoardState gameOverState = new TTTBoardState(player1Mv, player2Mv, player1Id, player2Id, currentBoard);
                gameOverState.setGameOver();
                return gameOverState;
            } else {
                return makeCurrentPlayerMove(player1, player2, currentBoard);
            }
        }
    }

    private static ArrayList<String> makeHumanMove(String playerMv, int move, ArrayList<String> currentBoard) {
        return board.placeMove(currentBoard, playerMv, move);
    }

    private static ArrayList<String> makeAiMove(String aiPlayer, ArrayList<String> currentBoard) {
        int move = ai_player.getMove(aiPlayer, currentBoard);
        return board.placeMove(currentBoard, aiPlayer, move);
    }

    private static boolean gameOver(ArrayList<String> board, String player1Mv, String player2Mv) {
        return rules.gameOver(board, player1Mv, player2Mv);
    }

    private static HashMap<String, String> currentPlayer(ArrayList<String> board, HashMap<String, String> player1, HashMap<String, String> player2) {
        int player1Moves = countPlayerMoves(player1.get("move"), board);
        int player2Moves = countPlayerMoves(player2.get("move"), board);

        if (player1Moves > player2Moves) {
            return player2;
        } else {
            return player1;
        }
    }

    private static boolean humanPlayer(HashMap<String, String> currentPlayer) {
        return currentPlayer.get("id").equals("human");
    }

    private static int countPlayerMoves(String player, ArrayList<String> board) {
        int counter = 0;
        for (String cell : board) {
            if (cell.equals(player)) {
                counter += 1;
            }
        }
        return counter;
    }

}
