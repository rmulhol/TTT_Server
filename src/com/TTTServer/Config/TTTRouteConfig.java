package com.TTTServer.Config;

import com.TTTServer.Responders.TTTHomePageResponder;
import com.httpServer.Router.RouteRegistrar;

class TTTRouteConfig {

    public static void setupRoutes() {
        RouteRegistrar.addRoute("/tic_tac_toe", "GET", new TTTHomePageResponder());
    }

}
