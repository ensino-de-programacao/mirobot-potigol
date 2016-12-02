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

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
        String sendMessage;
        try {
////            sendMessage = bufferRead.readLine();
            sendMessage = "{\"cmd\": \"right\", \"id\": \"Ir7l002c\", \"arg\": \"90\"}";
            session.getBasicRemote().sendText(sendMessage);
        } catch (IOException ex) {;
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("Mirobot ...." + message);
            String sendMessage = bufferRead.readLine();
            session.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        latch.countDown();
    }
    
    public String sendMessage() {
        return "";
    }

    public static void main(String[] args)  {
        latch = new CountDownLatch(1);

        ClientManager client = ClientManager.createClient();
        try {
            client.connectToServer(SocketHandler.class, new URI("ws://localhost:8899"));
            latch.await();

        } catch (IOException | DeploymentException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


//package br.edu.ifrn.mirobot;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.websocket.ClientEndpointConfig;
//import javax.websocket.DeploymentException;
//import javax.websocket.Endpoint;
//import javax.websocket.EndpointConfig;
//import javax.websocket.MessageHandler;
//import javax.websocket.Session;
//import org.glassfish.tyrus.client.ClientManager;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author mateus
// */
//public class SocketHandler {
//
//    private static CountDownLatch messageLatch;
//    private static final String SENT_MESSAGE = "{\"cmd\": \"right\", \"id\": \"Ir7l002c\", \"arg\": \"90\"}";
//
//    public static void run() {
//        try {
//            
//            List<String> entradas = new ArrayList();
//            
//            messageLatch = new CountDownLatch(1);
//
//            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
//
//            ClientManager client = ClientManager.createClient();
//            client.connectToServer(new Endpoint() {
//
//                @Override
//                public void onOpen(Session session, EndpointConfig config) {
//                    try {
//                        session.addMessageHandler(new MessageHandler.Whole<String>() {
//
//                            @Override
//                            public void onMessage(String message) {
//                                System.out.println("Received message: " + message);
//                                entradas.add(message);
//                                
//                                
//                                messageLatch.countDown();
//                            }
//                        });
//                        session.getBasicRemote().sendText(SENT_MESSAGE);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, cec, new URI("ws://localhost:8899"));
//            messageLatch.await(100, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public String generateId() {
//        return "";
//    }
//}
