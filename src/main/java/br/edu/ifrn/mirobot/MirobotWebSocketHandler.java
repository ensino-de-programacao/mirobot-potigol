package br.edu.ifrn.mirobot;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

@ClientEndpoint
public class MirobotWebSocketHandler {

    // apenas utilizado para o teste em main
    private static CountDownLatch latch;

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Session session;
    private final ClientManager client;

    public MirobotWebSocketHandler() {
        this.client = ClientManager.createClient();        
    }
    
    public void connect(String url) {
        try {
            this.client.connectToServer(this, new URI(url));
        } catch (IOException | DeploymentException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    } 
    
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.logger.info("Connected ... " + session.getId());
    }

    public void send(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
            this.logger.info("Send message: " + message);
        } catch (IOException ex) {
            this.logger.warning(MirobotWebSocketHandler.class.getName());
            throw new RuntimeException(ex);
        }
    }
    
    @OnMessage
    public void onMessage(String message) {
        this.logger.info("Received message: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        this.logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
    }
    
    public static void main(String[] args)  {
        MirobotWebSocketHandler.latch = new CountDownLatch(1);

        try {
            MirobotWebSocketHandler handler = new MirobotWebSocketHandler();
            handler.connect("ws://localhost:8899");
            handler.send("{\"cmd\": \"right\", \"id\": \"Ir7l002c\", \"arg\": \"90\"}");
            MirobotWebSocketHandler.latch.await();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}