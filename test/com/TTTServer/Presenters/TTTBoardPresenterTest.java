package com.TTTServer.Presenters;

import com.TTTServer.TTTLogic.TTTBoardState;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TTTBoardPresenterTest {

    @Test
    public void boardPresenterExists() {
        assertNotNull(new TTTBoardPresenter());
    }

    @Test
    public void presentsBoardInPlay() throws IOException {
        Path activeBoardPath = Paths.get(System.getProperty("user.dir") + "/test/com/TTTServer/Presenters/TTTExampleActiveBoard.txt");
        byte[] activeBoardPage = Files.readAllBytes(activeBoardPath);

        ArrayList<String> board = new ArrayList<String>();
        board.add(0, "X");
        board.add(1, "O");
        board.add(2, "X");
        board.add(3, " ");
        board.add(4, "O");
        board.add(5, "O");
        board.add(6, "O");
        board.add(7, "X");
        board.add(8, "X");

        TTTBoardState boardState = new TTTBoardState("X", "O", "human", "ai", board);

        assertArrayEquals(activeBoardPage, TTTBoardPresenter.presentBoard(boardState));
    }

    @Test
    public void presentsGameOverBoard() throws IOException {
        Path gameOverBoardPath = Paths.get(System.getProperty("user.dir") + "/test/com/TTTServer/Presenters/TTTExampleGameOverBoard.txt");
        byte[] gameOverBoardPage = Files.readAllBytes(gameOverBoardPath);

        ArrayList<String> board = new ArrayList<String>();
        board.add(0, "X");
        board.add(1, "O");
        board.add(2, "X");
        board.add(3, "X");
        board.add(4, "O");
        board.add(5, "O");
        board.add(6, "O");
        board.add(7, "X");
        board.add(8, "X");

        TTTBoardState boardState = new TTTBoardState("X", "O", "human", "ai", board);
        boardState.setGameOver();

        assertArrayEquals(gameOverBoardPage, TTTBoardPresenter.presentBoard(boardState));
    }
}