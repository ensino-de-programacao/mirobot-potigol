package br.edu.ifrn.mirobot;

public class Mirobot {

    public static Mirobot usar(String url) {
        return new Mirobot(url);
    }

    private Mirobot(String endereco) {
        super();
        this.conectar(endereco);

    }

    public void conectar(String endereco) {
    }

    public void autoConectar() {
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

    public void alertar() {
        this.enviar("beep");
    }

    public void collideState() {
    }

    public void seguirEstado() {
    }

    private void enviar(String comando) {
    }
}
