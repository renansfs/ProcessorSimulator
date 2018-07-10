/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

/**
 *
 * Escalonador
 */
public class SchedulerFCFS implements Scheduler{ 
    
    /*
    * Cabeça da lista do escalonador
    */
    public int contador;
    public Element head;
    Element last;
    
    public void addEvent(int chegada, int tamanho, String nome, int prioridade, int job, int[] cm){
        contador++;
         if(head == null){
             head = new Element(nome, chegada, tamanho, job, cm);
             last = head;
         } 
         else{
             Element temp = head;
             do{
                 if(temp.next == null){
                     temp.next = new Element(nome, chegada, tamanho, job, cm);
                     last = temp.next;
                     break;
                 }
                 else temp = temp.next;
             }while(true);
         }
    }
    public Element getEvent(int tempo){ //pega o próximo evento
        if(head == null) return null;
        Element temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }
    public Element getLocal(){
        return last;
    }
    
    //nao existe interrupcao no FCFS, entao sempre retonra falso
    public boolean interrupcao(int tempo, int falta){
        return false;
    }

    @Override
    public String head() {
        if(head == null) return "END";
        return head.nome;
    }

    @Override
    public Element get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
