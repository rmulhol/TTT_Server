package com.TTTServer.Responders;

import com.TTTServer.Presenters.TTTBoardPresenter;
import com.TTTServer.TTTLogic.TTTBoardState;
import com.TTTServer.TTTLogic.TTTGameLogic;
import com.httpServer.Handlers.Handler;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class TTTUpdateBoardResponder implements Handler {

    private Response tttUpdateBoardResponse;
    private Request request;

    @Override
    public Response respondToRequest(Request request) {
        tttUpdateBoardResponse = new Response();
        this.request = request;
        setStatus();
        setHeader();
        setBody();
        return tttUpdateBoardResponse;
    }

    private void setStatus() {
        tttUpdateBoardResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        tttUpdateBoardResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        TTTBoardState initialState = new TTTBoardState(request.getBody());
        TTTBoardState updatedState = TTTGameLogic.getNextState(initialState);
        tttUpdateBoardResponse.setBody(TTTBoardPresenter.presentBoard(updatedState));
    }
}
