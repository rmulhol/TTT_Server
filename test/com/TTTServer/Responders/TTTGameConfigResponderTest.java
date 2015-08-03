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

public class TTTGameConfigResponderTest {

    @Test
    public void respondsToGameConfigRequest() throws IOException {
        Request tttGameConfigRequest = new Request();
        tttGameConfigRequest.setMethod("GET");
        tttGameConfigRequest.setUri("/tic_tac_toe/config");
        Response tttGameConfigResponse = new TTTGameConfigResponder().respondToRequest(tttGameConfigRequest);

        assertArrayEquals(ResponseStatus.ok(), tttGameConfigResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), tttGameConfigResponse.getHeader());

        Path tttGameConfigPagePath = Paths.get(System.getProperty("user.dir") + "/src/com/TTTServer/Responders/Pages/TTTGameConfigPage.txt");
        byte[] tttGameConfigPage = Files.readAllBytes(tttGameConfigPagePath);

        assertArrayEquals(tttGameConfigPage, tttGameConfigResponse.getBody());
    }

}