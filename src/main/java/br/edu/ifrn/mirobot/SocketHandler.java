package br.edu.ifrn.mirobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
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
public class SocketHandler {

    private static CountDownLatch latch;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
        this.session = session;
    }

    public void send(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
            logger.info("Send message: " + message);
        } catch (IOException ex) {
            logger.warning(SocketHandler.class.getName());
            throw new RuntimeException(ex);
        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("Received message: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        latch.countDown();
    }
    
    public static void main(String[] args)  {
        latch = new CountDownLatch(1);

        ClientManager client = ClientManager.createClient();
        try {
            SocketHandler handler = new SocketHandler();
            client.connectToServer(handler, new URI("ws://localhost:8899"));
            handler.send("{\"cmd\": \"right\", \"id\": \"Ir7l002c\", \"arg\": \"90\"}");
            latch.await();

        } catch (IOException | DeploymentException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}