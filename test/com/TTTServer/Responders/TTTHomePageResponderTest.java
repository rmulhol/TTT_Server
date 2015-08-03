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

public class TTTHomePageResponderTest {

    @Test
    public void respondsToHomePageRequest() throws IOException {
        Request tttHomePageRequest = new Request();
        tttHomePageRequest.setMethod("GET");
        tttHomePageRequest.setUri("/tic_tac_toe");
        Response tttHomePageResponse = new TTTHomePageResponder().respondToRequest(tttHomePageRequest);

        assertArrayEquals(ResponseStatus.ok(), tttHomePageResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), tttHomePageResponse.getHeader());

        Path tttHomePagePath = Paths.get(System.getProperty("user.dir") + "/src/com/TTTServer/Responders/Pages/TTTHomePage.txt");
        byte[] tttHomePage = Files.readAllBytes(tttHomePagePath);

        assertArrayEquals(tttHomePage, tttHomePageResponse.getBody());
    }
}