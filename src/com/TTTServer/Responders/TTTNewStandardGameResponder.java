package com.TTTServer.Responders;

import com.httpServer.Handlers.Handler;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TTTNewStandardGameResponder implements Handler {

    private Response tttNewStandardGameResponse;

    @Override
    public Response respondToRequest(Request request) {
        tttNewStandardGameResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return tttNewStandardGameResponse;
    }

    private void setStatus() {
        tttNewStandardGameResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        tttNewStandardGameResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        try {
            Path tttNewStandardGamePagePath = Paths.get(System.getProperty("user.dir") + "/src/com/TTTServer/Responders/Pages/TTTNewStandardGamePage.txt");
            byte[] tttNewStandardGamePage = Files.readAllBytes(tttNewStandardGamePagePath);
            tttNewStandardGameResponse.setBody(tttNewStandardGamePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
