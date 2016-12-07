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
        this.lista.put("direita", (args) -> this.mirobot.girarParaDireita());
        this.lista.put("conectar", (args) -> this.mirobot.conectar(args[1]));
        this.lista.put("esquerda", (args) -> this.mirobot.girarParaEsquerda());
        // complementar com os outros comandos
    }
    
    void executar(String comando, String[] comandos) {
        if (this.lista.containsKey(comando))
            this.lista.get(comando).apply(comandos);
    }
}
