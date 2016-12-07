/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.mirobot.terminal;

/**
 *
 * @author leo
 */
@FunctionalInterface
public interface MirobotComando {
    public void apply(String[] args);
}
