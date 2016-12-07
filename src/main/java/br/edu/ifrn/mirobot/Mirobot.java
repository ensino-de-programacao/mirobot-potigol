/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.mirobot;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateus
 */
public class Mirobot {

    // apenas utilizado para o teste em main
    private static CountDownLatch latch;
    
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
        this.enviar("ping");
    }

    public void levantarCaneta() {
        this.enviar("penup");
    }

    public void descerCaneta() {
        this.enviar("pendown");
    }

    public void moverParaFrente(int distancia) {
        this.enviar("forward");
    }

    public void moverParaTras(int distancia) {
        this.enviar("back");
    }

    public void girarParaEsquerda() {
        this.enviar("left");
    }

    public void girarParaDireita() {
        this.enviar("rigth");
    }

    public void beep() {
        this.enviar("beep");
    }

    public void estadoDeColisao() {
    }

    public void seguimentoDeColisao() {
    }

    private void enviar(String comando) {
        this.socket.send("{\"cmd\": \"right\", \"id\": \"Ir7l002c\", \"arg\": \"90\"}");
    }

    public void rodar(String comando) {
        beep();
    }
    
    public static void main(String[] args) {
        // apenas para executar o teste
        Mirobot.latch = new CountDownLatch(1);
        
        Mirobot mirobot = new Mirobot();
        mirobot.conectar("ws://localhost:8899");
        mirobot.beep();
        
        // apenas para executar o teste
        try {
            Mirobot.latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mirobot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
