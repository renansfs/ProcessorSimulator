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
 * Memoria Principal, caso o espaco esteja livre, o valor da posicao x do endereco sera 0, caso nao, esta ocupado.
 */
public interface RAM {
     //memoria de 16 bits - 64kb
    int elements = 0;
    public Element[] fisica = new Element[65536];;
    boolean enviarProcesso();
    int getTamanho();
    void setEscalonador(Scheduler escalonador);
    void liberar(int[] espaco);
    void add(Element aux, int tam);
    boolean verificarBlocos(int tamanho);
}


 