/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.mirobot.terminal;

import br.edu.ifrn.mirobot.Mirobot;
import java.util.HashMap;
/**
 *
 * @author leo
 */
public class MirobotComandos {
    
    private Mirobot mirobot;
    private final HashMap<String, MirobotComando> lista = new HashMap<>();
    
    public MirobotComandos(Mirobot mirobot) {
        this.mirobot = mirobot;
        this.lista.put("ping", (args) -> this.mirobot.ping());
        this.lista.put("levantar", (args) -> this.mirobot.levantarCaneta());
        this.lista.put("descer", (args) -> this.mirobot.descerCaneta());
        this.lista.put("direita", (args) -> this.mirobot.direita(args[1]));
        this.lista.put("esquerda", (args) -> this.mirobot.esquerda(args[1]));
        this.lista.put("frente", (args) -> this.mirobot.moverParaFrente(args[1]));
        this.lista.put("tras", (args) -> this.mirobot.moverParaTras(args[1]));
        this.lista.put("beep", (args) -> this.mirobot.beep(args[1]));
        this.lista.put("estado", (args) -> this.mirobot.estadoDeColisao());
        this.lista.put("seguimento", (args) -> this.mirobot.seguimentoDeColisao());
        this.lista.put("conectar", (args) -> this.mirobot.conectar(args[1]));
        // complementar com os outros comandos
    }
    
    void executar(String comando, String[] comandos) {
        if (this.lista.containsKey(comando))
            this.lista.get(comando).apply(comandos);
    }
}
