/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

import java.io.IOException;

/**
 *
 * @author Galaxy
 */
public class SchedulerMFR implements Scheduler {

    public int contador, n, elementos, i = 0;
    public Scheduler[] filas;
    Scheduler atual;
    boolean aux = false;

    @Override
    public void addEvent(int chegada, int tamanho, String nome, int prioridade, int job, int[] cm) {
        elementos++;

        if (prioridade == 0) { //RR 8 SEG
            filas[0].addEvent(chegada, tamanho, nome, prioridade, job, cm);

        } else if (prioridade == 1) { //RR 16SEG
            filas[1].addEvent(chegada, tamanho, nome, prioridade, job, cm);

        } else if (prioridade >= 2) { //FCFS
            filas[2].addEvent(chegada, tamanho, nome, prioridade, job, cm);

        }
    }

    public void numeroFilas() throws IOException {
        filas = new Scheduler[3];
        filas[0] = new SchedulerRR();
        ((SchedulerRR) filas[0]).medium = 8;
        filas[1] = new SchedulerRR();
        ((SchedulerRR) filas[1]).medium = 16;
        filas[2] = new SchedulerFCFS();

    }

    @Override
    public Element getEvent(int tempo) {

        for (int a = 0; a < filas.length; a++) {
            Element temp = filas[a].getEvent(tempo);
            atual =filas[a];
            if (temp == null && a == filas.length - 1) {
                n=a;
                return temp;
            } else if (temp !=null) {
                n=a;
                return temp;
            }
            else if(temp == null){
                continue;
            }
        }

        return null;
    }

    @Override
    public Element getLocal() {
        return null;
    }

    @Override
    public boolean interrupcao(int tempo, int falta) {
        if (atual == null) {
            return false;
        }
        if (atual.interrupcao(tempo, falta)) {
            return true;
        } else {
            for (int k = 0; k < tempo; k++) {
                if (filas[k].head() != null) {
                    return true;
                }
            }
            return false;
        }

    }

    @Override
    public String head() {
        for (int a = n; a < filas.length; a++) {
            if (filas[a].head() != null) {
                return filas[a].head();
            }
        }
        return "END";

    }

    @Override
    public Element get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}