package tms.web_calcs;
import com.sun.net.httpserver.HttpServer;
import tms.web_calcs.handlers.CalculatingHandler;
import tms.web_calcs.handlers.ConvertHandler;
import tms.web_calcs.handlers.GreetingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/greeting", new GreetingHandler());
        server.createContext("/calc", new CalculatingHandler());
        server.createContext("/convert", new ConvertHandler());

        server.start();
    }
}