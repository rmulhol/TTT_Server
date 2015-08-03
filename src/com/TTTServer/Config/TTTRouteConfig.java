package com.TTTServer.Config;

import com.TTTServer.Responders.TTTGameConfigResponder;
import com.TTTServer.Responders.TTTHomePageResponder;
import com.TTTServer.Responders.TTTNewStandardGameResponder;
import com.httpServer.Router.RouteRegistrar;

public class TTTRouteConfig {

    public static void setupRoutes() {
        RouteRegistrar.addRoute("/tic_tac_toe", "GET", new TTTHomePageResponder());
        RouteRegistrar.addRoute("/tic_tac_toe/config", "GET", new TTTGameConfigResponder());
        RouteRegistrar.addRoute("/tic_tac_toe/game", "GET", new TTTNewStandardGameResponder());
    }

}
