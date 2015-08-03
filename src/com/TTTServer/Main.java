package com.TTTServer;

import com.httpServer.Config.LoggerConfig;
import com.httpServer.Config.RouteConfig;
import com.httpServer.Config.ServerConfig;
import com.httpServer.Runner.ServerRunner;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        LoggerConfig.configureLogs();
        ServerConfig.configureServer(args);

        RouteConfig.setupRoutes();

        new ServerRunner().run();
    }
}
