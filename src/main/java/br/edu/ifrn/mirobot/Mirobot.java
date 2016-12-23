/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.mirobot;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author mateus
 */
public class Mirobot {

    // apenas utilizado para o teste em main
    private static CountDownLatch latch;

    private static Mirobot instancia;

    private MirobotWebSocketHandler socket;

    public Mirobot() {
        super();
        this.socket = new MirobotWebSocketHandler();
    }

    public void conectar(String endereco) {
        this.socket.connect(endereco);
    }

    public void conectarAuto() {
    }

    public void desconectar() {
    }

    public void ping() {
        this.enviar("ping", "");
    }

    public void levantarCaneta() {
        this.enviar("penup", "");
    }

    public void descerCaneta() {
        this.enviar("pendown", "");
    }

    public void moverParaFrente(String distancia) {
        this.enviar("forward", distancia);
    }

    public void moverParaTras(String distancia) {
        this.enviar("back", distancia);
    }

    public void esquerda(String graus) {
        this.enviar("left", graus);
    }

    public void direita(String graus) {
        this.enviar("rigth", graus);
    }

    public void beep(String segundos) {
        this.enviar("beep", segundos);
    }

    public void estadoDeColisao() {
    }

    public void seguimentoDeColisao() {
    }

    private void enviar(String comando, String arg) {
        if (!arg.equals("")) {
            this.socket.send("{\"cmd\": \"" + comando + "\", \"id\": \""
                    + this.gerarId() + "\", \"arg\": \"" + arg + "\"}");
        } else {
            this.socket.send("{\"cmd\": \"" + comando + "\", \"id\": \""
                    + this.gerarId() + "\"}");
        }
        //   this.socket.send("{\"cmd\": \"right\", \"id\": \"Ir7l002c\", \"arg\": \"90\"}");
    }

    private String gerarId() {
        return "Ir7l002c";
    }

    public static Mirobot pegarInstancia() {
        if (Mirobot.instancia == null) {
            Mirobot.instancia = new Mirobot();

            return Mirobot.instancia;
        }

        return Mirobot.instancia;
    }

//    public static void main(String[] args) {
//        // apenas para executar o teste
//        Mirobot.latch = new CountDownLatch(1);
//        
//        Mirobot mirobot = new Mirobot();
//        mirobot.conectar("ws://localhost:8899");
//        
//        // apenas para executar o teste
//        try {
//            Mirobot.latch.await();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Mirobot.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
