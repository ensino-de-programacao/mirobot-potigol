/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.mirobot;

/**
 *
 * @author mateus
 */
public class Mirobot {

    private SocketHandler socket;

    public Mirobot() {
        super();
        socket = new SocketHandler();
    }
    
    public void conectar(String endereco) {
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
    }

    public static void rodar() {
        SocketHandler.run();
    }
}
