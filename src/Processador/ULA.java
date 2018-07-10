/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processador;

import Memory.RAM;
import Scheduler.Element;

/**
 *
 * @author Galaxy
 */
public class ULA { //ULA PREEMPTIVA

    private int clock = 1;
    UC unidade;
    boolean modo;

    /**
     * @param args Unidade de processamento aritmetico
     */
    public ULA(UC unidade) {
        this.unidade = unidade;
    }

    public int[] processar(Element processado, int tempo, GM memory) {
        if (processado == null) {
            return new int[]{-1, -1};
        }
        processado.entrada = tempo; //hora que entra no processador
        int[] result = new int[2];
        result[0] = tempo; //recebe o tempo atual
        result[1] = -1; // calculo da interrupcao e quanto falta para terminar
        while(memory.verificar(result[0]) != 0 && memory.verificar(result[0]) != -1); //verifica no tempo
        int i = processado.timeburst;
        do {
            result[1] = --i;
            result[0]++;
            while(memory.verificar(result[0]) != 0 && memory.verificar(result[0]) != -1);
            if ((unidade.atual.interrupcao(processado.pri, i)) && (i > 0)) { //verifica a cada tempo se existe interrupcao   
                return result;
            }
        } while (i > 0);
        
        return result;
    }
}
