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

public class TTTNewStandardGameResponderTest {

    @Test
    public void respondsToNewStandardGameResponse() throws IOException {
        Request tttNewStandardGameRequest = new Request();
        tttNewStandardGameRequest.setMethod("GET");
        tttNewStandardGameRequest.setUri("/tic_tac_toe/game");
        Response tttNewStandardGameResponse = new TTTNewStandardGameResponder().respondToRequest(tttNewStandardGameRequest);

        assertArrayEquals(ResponseStatus.ok(), tttNewStandardGameResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), tttNewStandardGameResponse.getHeader());

        Path tttNewStandardGamePagePath = Paths.get(System.getProperty("user.dir") + "/src/com/TTTServer/Responders/Pages/TTTNewStandardGamePage.txt");
        byte[] tttNewStandardGamePage = Files.readAllBytes(tttNewStandardGamePagePath);

        assertArrayEquals(tttNewStandardGamePage, tttNewStandardGameResponse.getBody());
    }

}