/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

/**
 *
 * @author Galaxy
 */
public interface Scheduler {
    void addEvent(int chegada, int tamanho, String nome, int prioridade, int job, int[] cm);
    Element getEvent(int tempo);
    Element getLocal();
    String head();
    boolean interrupcao(int tempo, int falta);
    Element get();
    
}
