

package Lista;

import Scheduler.Element;

/**
 *
 * Lista inicial, no qual recebe os eventos
 * 
 */

public class Lista {
    
    public int elementos;
    public Element head;
    Element last;

    public void add(int chegada, int tamanho, String nome, int prioridade, int job, int[] cm){      
        elementos++;
        int[] prov = {0, 0};
         if(head == null){ //se nao tem nada na fila, adiciona no comeco
             head = new Element(nome, chegada, tamanho, job, prov);
             last = head;
         } 
         else{ //caso nao, adiciona na fila
             
             Element temp = head;
             do{
                 if(temp.next == null){
                     temp.next = new Element(nome, chegada, tamanho, job, prov);
                     last = temp.next;
                     break;
                 }
                 else temp = temp.next;
             }while(true);
         }
    }
    
    //pega o próximo evento
    
    public Element getEvent(Element a){ 
        Element temp = head;
        //se nao existe mais nada na fila, retorna null
        if(temp == null) return null;
        //se a cabeca e o que procura, retorna null
        else if(temp.job == a.job){
                head = head.next;
                return temp;
        }else{
        //procura o evento
        for(Element aux = head.next; aux!= null; aux = aux.next){
            if(a.job == aux.job){
                temp.next = aux.next;
                return aux;
            }
            temp = temp.next;
        }
        return null;
        }
    }
    
    public Element getHead(){
        
        return head; //retorna a referencia da cabeça
    }
    
    //verifica o tempo do próximo
    public int verificarHead() {
        if(head == null) return -1;
        return head.chegada;
    }
}
