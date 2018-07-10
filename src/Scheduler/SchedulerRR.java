/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

/**
 *
 * @author Galaxy
 */
public class SchedulerRR implements Scheduler{
    /*
     * Cabeça da lista do escalonador
     */
    public int total, elementos, medium, chamadas, contador;
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
         total();
    }

    public Element getEvent(int tempo) { //pega o próximo evento
        if (head == null) {
            return null;
        }
        Element temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }

    public Element getLocal() {
        return last;
    }

   public void total(){
        for(Element aux = head; aux!=null;aux = aux.next){
            elementos++;
            total = aux.timeburst + total;
            medium = total/elementos;
        }
    }
    
    public boolean interrupcao(int tempo, int falta) {
        chamadas++;
        if(falta == 0) chamadas = 0;
        
        if(chamadas == medium){
            chamadas = 0;
            return true;
        }
        return false;
    }

    @Override
    public String head() {
        if(head == null) return "END";
        return head.nome;
    }

    @Override
    public Element get() {
        return head;
        }
}
