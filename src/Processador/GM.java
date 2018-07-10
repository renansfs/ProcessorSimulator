/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processador;

import Lista.Lista;
import Memory.RAM;
import Scheduler.Element;
import Scheduler.*;

/**
 *
 * Gerencia de Memoria
 */
public class GM {

    Lista lista;
    RAM memoria;
    
    
    public GM(Lista lista, RAM memoria) {
        this.lista = lista;
        this.memoria = memoria;
    }

    //verifica na lista se o tempo chegou e se e do tamanho
    public int verificar(int tempo) {

        Element aux = lista.getHead(); //pega a cabeça
        
        while (true) {
            if (aux == null) {
                return -1; //caso nao possua mais nada na fila
            } else if (aux.chegada <= tempo) { //se esta no tempo 
                int pag = aux.timeburst / 32; //divisao dos blocos de memoria     
                double verificador = aux.timeburst % 32; //verifica se existe resto da divisao
                if (verificador != 0) pag = pag + 1; //caso exista, é colocado mais um bloco.
                
                if (memoria.verificarBlocos(pag)) { //verifica se existe espaco suficiente de memoria
                    memoria.add(lista.getEvent(aux), pag); //adiciona na memoria o evento, retirando da fila de chegada
                    return 1;
                }

            } else {
                return 0;
            }
            aux = aux.next;
        }
    }
    
       
    public void liberar(int[] espaco){
        memoria.liberar(espaco);
    }
}
