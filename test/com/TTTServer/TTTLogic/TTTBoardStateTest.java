package com.TTTServer.TTTLogic;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TTTBoardStateTest {


    @Test
    public void constructsBoardPropertiesFromPostParamsWithoutMove() {
        String rawTTTBoardState = "player-1-id=human&player-2-id=ai&player-1-mv=X&player-2-mv=O&board=+%2C+%2C+%2C+%2C+%2C+%2C+%2C+%2C+";
        TTTBoardState tttBoardState = new TTTBoardState(rawTTTBoardState);

        assertEquals("human", tttBoardState.getPlayer1Id());
        assertEquals("ai", tttBoardState.getPlayer2Id());
        assertEquals("X", tttBoardState.getPlayer1Mv());
        assertEquals("O", tttBoardState.getPlayer2Mv());

        ArrayList<String> emptyBoard = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            emptyBoard.add(i, " ");
        }

        assertEquals(emptyBoard, tttBoardState.getBoard());
        assertTrue(!tttBoardState.hasMove());
    }

    @Test
    public void constructsBoardPropertiesFromPostParamsWithMove() {
        String rawTTTBoardState = "player-1-id=human&player-2-id=ai&player-1-mv=X&player-2-mv=O&board=+%2C+%2C+%2C+%2C+%2C+%2C+%2C+%2C+&move=1";
        TTTBoardState tttBoardState = new TTTBoardState(rawTTTBoardState);

        assertEquals("human", tttBoardState.getPlayer1Id());
        assertEquals("ai", tttBoardState.getPlayer2Id());
        assertEquals("X", tttBoardState.getPlayer1Mv());
        assertEquals("O", tttBoardState.getPlayer2Mv());

        ArrayList<String> emptyBoard = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            emptyBoard.add(i, " ");
        }

        assertEquals(emptyBoard, tttBoardState.getBoard());
        assertTrue(tttBoardState.hasMove());
        assertEquals(1, tttBoardState.getMove());
    }

    @Test
    public void constructsBoardPropertiesFromPassedInput() {
        ArrayList<String> emptyBoard = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            emptyBoard.add(i, " ");
        }

        TTTBoardState tttBoardState = new TTTBoardState("X", "O", "human", "ai", emptyBoard);

        assertEquals("X", tttBoardState.getPlayer1Mv());
        assertEquals("O", tttBoardState.getPlayer2Mv());
        assertEquals("human", tttBoardState.getPlayer1Id());
        assertEquals("ai", tttBoardState.getPlayer2Id());
        assertEquals(emptyBoard, tttBoardState.getBoard());
    }

    @Test
    public void togglesGameOver() {
        ArrayList<String> emptyBoard = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            emptyBoard.add(i, " ");
        }

        TTTBoardState tttBoardState = new TTTBoardState("X", "O", "human", "ai", emptyBoard);
        assertTrue(!tttBoardState.isGameOver());
        tttBoardState.setGameOver();
        assertTrue(tttBoardState.isGameOver());
    }

}