package com.TTTServer.Responders;

import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TTTUpdateBoardResponderTest {

    @Test
    public void respondsToUpdateBoardRequest() throws IOException {
        Request tttUpdateBoardRequest = new Request();
        tttUpdateBoardRequest.setMethod("POST");
        tttUpdateBoardRequest.setUri("/tic_tac_toe/game");
        tttUpdateBoardRequest.setBody("player-1-id=human&player-2-id=ai&player-1-mv=X&player-2-mv=O&board=X%2CO%2CX%2C+%2CO%2CO%2CO%2CX%2CX&move=4");
        Response tttUpdateBoardResponse = new TTTUpdateBoardResponder().respondToRequest(tttUpdateBoardRequest);

        assertArrayEquals(ResponseStatus.ok(), tttUpdateBoardResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), tttUpdateBoardResponse.getHeader());

        Path gameOverBoardPath = Paths.get(System.getProperty("user.dir") + "/test/com/TTTServer/Presenters/TTTExampleGameOverBoard.txt");
        byte[] gameOverBoardPage = Files.readAllBytes(gameOverBoardPath);

        assertArrayEquals(gameOverBoardPage, tttUpdateBoardResponse.getBody());

    }

}