/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.mirobot.terminal;

import br.edu.ifrn.mirobot.Mirobot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leo
 */
public class MirobotTerminal {
    
    private BufferedReader bufferRead;
    private Mirobot mirobot;
    private final MirobotComandos comandos;
    
    public MirobotTerminal() {
        this.bufferRead = new BufferedReader(new InputStreamReader(System.in));
        this.mirobot = new Mirobot();
        this.comandos = new MirobotComandos(this.mirobot);
    }       
            
    private void executar() {
        try {
//            mirobot.conectar("ws://localhost:8899");
            while(true) {
                String linha = this.bufferRead.readLine();
                String[] comandos = linha.split(" ");
                String comando = comandos[0];
                if (comando.equals("sair")) return;
                this.comandos.executar(comando, comandos);
            }
        } catch (IOException ex) {
            Logger.getLogger(MirobotTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new MirobotTerminal().executar();
        
    }

}
