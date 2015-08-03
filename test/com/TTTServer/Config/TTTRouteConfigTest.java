package com.TTTServer.Config;

import com.TTTServer.Responders.TTTGameConfigResponder;
import com.TTTServer.Responders.TTTHomePageResponder;
import com.TTTServer.Responders.TTTNewStandardGameResponder;
import com.TTTServer.Responders.TTTUpdateBoardResponder;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.Router.RouteRegistrar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TTTRouteConfigTest {

    @Before
    public void clearRoutes() {
        RouteRegistrar.clearRoutes();
        TTTRouteConfig.setupRoutes();
    }

    @Test
    public void tttRouteConfigExists() {
        assertNotNull(new TTTRouteConfig());
    }

    @Test
    public void addsTTTHomePageRoute() {
        Request tttHomePageRequest = new Request();
        tttHomePageRequest.setMethod("GET");
        tttHomePageRequest.setUri("/tic_tac_toe");
        assertEquals(TTTHomePageResponder.class, RouteRegistrar.getRoute(tttHomePageRequest).getClass());
    }

    @Test
    public void addsTTTGameConfigRoute() {
        Request tttGameConfigRequest = new Request();
        tttGameConfigRequest.setMethod("GET");
        tttGameConfigRequest.setUri("/tic_tac_toe/config");
        assertEquals(TTTGameConfigResponder.class, RouteRegistrar.getRoute(tttGameConfigRequest).getClass());
    }

    @Test
    public void addsTTTNewStandardGameRoute() {
        Request tttNewStandardGameRequest = new Request();
        tttNewStandardGameRequest.setMethod("GET");
        tttNewStandardGameRequest.setUri("/tic_tac_toe/game");
        assertEquals(TTTNewStandardGameResponder.class, RouteRegistrar.getRoute(tttNewStandardGameRequest).getClass());
    }

    @Test
    public void addsTTTUpdateBoardRoute() {
        Request tttUpdateBoardRequest = new Request();
        tttUpdateBoardRequest.setMethod("POST");
        tttUpdateBoardRequest.setUri("/tic_tac_toe/game");
        assertEquals(TTTUpdateBoardResponder.class, RouteRegistrar.getRoute(tttUpdateBoardRequest).getClass());
    }
}