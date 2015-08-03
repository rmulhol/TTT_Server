package com.TTTServer.TTTLogic;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TTTGameLogicTest {

    @Test
    public void tttGameLogicExists() {
        assertNotNull(new TTTGameLogic());
    }

    @Test
    public void returnsUpdatedBoardStateForHumanMove() {
        TTTBoardState gameInProgress = new TTTBoardState("player-1-id=human&player-2-id=ai&player-1-mv=X&player-2-mv=O&board=X%2CO%2C+%2C+%2CO%2C+%2CO%2CX%2CX&move=3");
        TTTBoardState updatedBoardState = TTTGameLogic.getNextState(gameInProgress);

        ArrayList<String> updatedBoard = new ArrayList<String>();
        updatedBoard.add(0, "X");
        updatedBoard.add(1, "O");
        updatedBoard.add(2, "X");
        updatedBoard.add(3, " ");
        updatedBoard.add(4, "O");
        updatedBoard.add(5, "O");
        updatedBoard.add(6, "O");
        updatedBoard.add(7, "X");
        updatedBoard.add(8, "X");

        assertEquals(updatedBoard, updatedBoardState.getBoard());
    }

    @Test
    public void cyclesAiMovesUntilGameOverIfBothPlayersAi() {
        ArrayList<String> inProgressBoard = new ArrayList<String>();
        inProgressBoard.add(0, "X");
        inProgressBoard.add(1, "O");
        inProgressBoard.add(2, " ");
        inProgressBoard.add(3, " ");
        inProgressBoard.add(4, "O");
        inProgressBoard.add(5, " ");
        inProgressBoard.add(6, " ");
        inProgressBoard.add(7, " ");
        inProgressBoard.add(8, "X");

        TTTBoardState aiVsAiGame = new TTTBoardState("X", "O", "ai", "ai", inProgressBoard);
        TTTBoardState gameConclusion = TTTGameLogic.getNextState(aiVsAiGame);

        assertTrue(gameConclusion.isGameOver());
    }

}