package br.ifrn.edu.mirobot;

public class Mirobot {
    private WebSocketHandler socket;
    public Mirobot() {
        super();
        socket = new WebSocketHandler();
    }
    
    public Mirobot(String endereco) {
        super();
        socket = new WebSocketHandler();
        this.conectar(endereco);
        
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
    public void collideState() {
    }
    public void fallowState() {
    }

    private void enviar(String comando) {
    }
}
