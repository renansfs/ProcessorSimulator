/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

/**
 *
 * @author Galaxy
 */
public class Element {
    //job table
    public Element(String nome, int chegada, int time, int job, int[] cm){ //recebe nome, hora de chegada, tamanho, identificador, espaco de memoria
        entrada = 0;
        this.chegada = chegada; //chegada do evento
        this.timeburst = time; //event time
        this.nome = nome; // nome do job
        this.job = job; //job indentifier
        control = false;
        this.cm = cm;
    }
    
    //adiciona a tabela de referencia a memoria
    public void setCM(int[] cm){
        this.cm = cm;
    }
    public int[] getCM(){ //retorna as referencias de memoria
        return cm;
    }
    
    //job table
    public boolean control;
    public int pri, entrada, timeburst, job;
    public int[] cm;
    public final String nome;
    public final int chegada;
    public int esc = 0;
    
   public Element next; //proximo evento
   
}
