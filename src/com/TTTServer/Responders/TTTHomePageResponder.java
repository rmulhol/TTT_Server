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

public class TTTHomePageResponder implements Handler {
    private Response tttHomePageResponse;

    @Override
    public Response respondToRequest(Request request) {
        tttHomePageResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return tttHomePageResponse;
    }

    private void setStatus() {
        tttHomePageResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        tttHomePageResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        try {
            Path tttHomePagePath = Paths.get(System.getProperty("user.dir") + "/src/com/TTTServer/Responders/Pages/TTTHomePage.txt");
            byte[] tttHomePage = Files.readAllBytes(tttHomePagePath);
            tttHomePageResponse.setBody(tttHomePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
