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

public class TTTGameConfigResponder implements Handler {

    Response tttGameConfigResponse;

    @Override
    public Response respondToRequest(Request request) {
        tttGameConfigResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return tttGameConfigResponse;
    }

    private void setStatus() {
        tttGameConfigResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        tttGameConfigResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        try {
            Path tttGameConfigPagePath = Paths.get(System.getProperty("user.dir") + "/src/com/TTTServer/Responders/Pages/TTTGameConfigPage.txt");
            byte[] tttGameConfigPage = Files.readAllBytes(tttGameConfigPagePath);
            tttGameConfigResponse.setBody(tttGameConfigPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
