    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

/**
 *
 * @author Galaxy
 */
public class SchedulerSJF implements Scheduler {
    /*
     * Cabeça da lista do escalonador
     */

    public int contador;
    public Element head;
    Element last;

    public void addEvent(int chegada, int tamanho, String nome, int prioridade, int job, int[] cm) {
        contador++;
        Element aux = head;
        boolean control = false;
        
        if (head == null) { //adiciona a cabeça
            head = new Element(nome, chegada, tamanho, job, cm);
            last = head;
            return;
            
        }else if(head.next==null){//adiciona o proxima a cabeca
            if(head.timeburst > tamanho){
                head = new Element(nome, chegada, tamanho, job, cm);
                head.next = aux;
            }else{
                head.next = new Element(nome, chegada, tamanho, job, cm);
            }
        }else{ //adiciona os demais
            Element temp = head.next;
            do {
                if (temp.next != null) {
                        if (temp.timeburst > tamanho) {
                                aux.next = new Element(nome, chegada, tamanho, job, cm); 
                                aux.next.next = temp;
                                return;                                
                        } 
                }else{
                    if(temp.timeburst > tamanho){
                        aux.next = new Element(nome, chegada, tamanho, job, cm); 
                        aux.next.next = temp;
                    }else{
                        temp.next = new Element(nome, chegada, tamanho, job, cm);
                    }
                    return;
                }
                aux = aux.next;
                temp = temp.next;
            } while (true);

        }
    }

    public Element getEvent(int tempo) { //pega o próximo evento
        if (head == null) { //caso nao existe mais nada na fila de prontos, retorna null
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

    public boolean interrupcao(int tempo, int falta) {
        return false;
    }

    @Override
    public String head() {
        if (head == null) {
            return "END";
        }
        return head.nome;
    }

    @Override
    public Element get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
