package br.com.academico.config;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
  
    public static final URI BASE_URI = URI.create("http://localhost:8080/academico-web-api/");

    public static HttpServer startServer() {

        final ResourceConfig resourceConfig = new AcademicoResourceConfig();

        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);

    }

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        System.out.println("-------------------------------------");    
		System.out.println("Servidor Iniciado");  
		System.out.println("Pressione enter para parar o servidor");
        System.in.read();
		server.shutdown();
		System.exit(0);
    }

}
