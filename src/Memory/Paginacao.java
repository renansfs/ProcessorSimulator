/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Memory;

import Scheduler.Element;
import Scheduler.Scheduler;

/**
 *
 * Parte da memoria de paginacao
 */
public class Paginacao implements RAM{
 public Element[] fisica = new Element[65536];; //ponteiro para memoria
    int paginas, sobras; //paginas - numero de paginas sobras - numero de paginas restantes
    Scheduler escalonador; //escalonador atual a ser usado
    int elements = 0; //numero de elementos na memoria
    
    public Paginacao(){
        paginas = fisica.length/32; //paginacao de 32 blocos, resultando 2048 espacos de memoria
        sobras = paginas;
    }
    
    
    public void setEscalonador(Scheduler escalonador){
        this.escalonador = escalonador;
    }
    @Override
    public int getTamanho() {
        return paginas; //retorna o tamanho atual
    }
    
    public int getSobras(){
        return sobras;
    }
    
    //verifica se chegou o tempo para enviar ou se existe mais processos
    public boolean enviarProcesso(){
       
       int i=0;
       boolean control = false; //os processos que nao estao a fila de prontos, ficam no estado de false
        while(fisica[i] != null){
            
            if(fisica[i].control == false) { //envia o processo que chegou
                escalonador.addEvent(fisica[i].chegada,fisica[i].timeburst, fisica[i].nome, fisica[i].pri, fisica[i].job, fisica[i].getCM());
                fisica[i].control = true;
            }
           
           i++;
       }
        return false; //tem eventos na pilha
    }    
    
    //caso termine o processo, ele sera liberado da memoria
    public void liberar(int[] espaco){
        for(int i = 0; i<espaco.length; i++){
            int a = espaco[i];
            for (int b = espaco[i]; b<a+32; b++){
                fisica[b] = null;
            }
        }
        sobras = espaco.length+sobras;
       
    }
    
    //verifica se possui paginas necessarias disponiveis
    public boolean verificarBlocos(int tamanho){
            if(tamanho <= sobras) return true;
            return false;
    }   

    //adiciona o elemento na memoria
    @Override
    public void add(Element aux, int tam) { //adiciona o processo na memória
        
         
        int[] jobtable = new int[tam]; //array do evento, passado por metodo.
        int count = 0; //contador
        sobras = sobras - tam; //diminui o tamanho
        
        for(int i=1; tam > 0; i++){
                if(fisica[i-1] == null){ //se a posicao i da memoria esta vazia
                       fisica[i-1] = aux;
                  if(i%32==0){
                        jobtable[count] = i-32;
                        count ++;
                        tam--;
                    } //subtrai, para verificar quanto falta, conforme os multiplos de 32
                }
            }   
            
            aux.setCM(jobtable); //envia a tabela de referencia
            enviarProcesso(); //envia o processo pronto para a fila de prontos.
        }//adiciona a memoria
     
    
    
}
