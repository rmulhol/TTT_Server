package com.TTTServer.TTTLogic;

import java.util.ArrayList;
import java.util.Arrays;

public class TTTBoardState {

    private String player1Id;
    private String player2Id;
    private String player1Mv;
    private String player2Mv;
    private ArrayList<String> board;
    private int move;
    private boolean hasMove = false;
    private boolean gameOver = false;

    public TTTBoardState(String postParams) {
        assignParams(postParams);
    }

    public TTTBoardState(String player1Mv, String player2Mv, String player1Id, String player2Id, ArrayList<String> board) {
        this.player1Mv = player1Mv;
        this.player2Mv = player2Mv;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.board = board;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public String getPlayer2Id() {
        return  player2Id;
    }

    public String getPlayer1Mv() {
        return player1Mv;
    }

    public String getPlayer2Mv() {
        return player2Mv;
    }

    public ArrayList<String> getBoard() {
        return board;
    }

    public boolean hasMove() {
        return hasMove;
    }

    public int getMove() {
        return move;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        gameOver = true;
    }

    private void assignParams(String postParams) {
        String decodedParams = decodeParams(postParams);
        String[] splitParams = splitParams(decodedParams);
        parseParams(splitParams);
    }

    private void parseParams(String[] splitParams) {
        for (int i = 0; i < splitParams.length; i++) {
            if (splitParams[i].equals("player-1-id")) {
                this.player1Id = splitParams[++i];
            } else if (splitParams[i].equals("player-2-id")) {
                this.player2Id = splitParams[++i];
            } else if (splitParams[i].equals("player-1-mv")) {
                this.player1Mv = splitParams[++i];
            } else if (splitParams[i].equals("player-2-mv")) {
                this.player2Mv = splitParams[++i];
            } else if (splitParams[i].equals("board")) {
                this.board = stringToBoard(splitParams[++i]);
            } else if (splitParams[i].equals("move")) {
                this.move = Integer.parseInt(splitParams[++i]);
                this.hasMove = true;
            }
        }
    }

    private String[] splitParams(String decodedParams) {
        return decodedParams.split("&");
    }

    private String decodeParams(String postParams) {
        return postParams.replace("%2C", ",").replace("+", " ").replace("=", "&");
    }

    ArrayList<String> stringToBoard(String board) {
        return new ArrayList<String>(Arrays.asList(board.split(",")));
    }
}
