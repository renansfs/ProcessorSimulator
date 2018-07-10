/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Galaxy
 */
public class SchedulerMF implements Scheduler {

    public int contador, n = 0, elementos, i;
    public Scheduler[] filas;
    Scheduler atual;

    /*
     *
     */
    @Override
    public void addEvent(int chegada, int tamanho, String nome, int prioridade, int job, int[] cm) {

        elementos++;
        if (prioridade >= filas.length) {
            prioridade = filas.length - 1;
        } else if (prioridade < 0) {
            prioridade = 0;
        }
        filas[prioridade].addEvent(chegada, tamanho, nome, prioridade, job, cm);
    }

    public void numeroFilas(int num) throws IOException {
        filas = new Scheduler[num];
        atribuirScheduler();
    }

    private void atribuirScheduler() throws IOException {
        for (i = 0; i < filas.length; i++) {
            executar(i);
        }

    }

    void executar(int i) {
        System.out.println("\n Digte o numero do Escalonador da prioridade: " + i + "\n");
        int b = -1;
        Scanner in = new Scanner(System.in);
        while (b == -1) {
            try {
                System.out.println("1- FCFS \n 2- SJF \n 3-SRTF \n 4-RR \n");

                b = in.nextInt();
                switch (b) {
                    case 1:
                        filas[i] = new SchedulerFCFS();
                        break;
                    case 2:
                        filas[i] = new SchedulerSJF();
                        break;
                    case 3:
                        filas[i] = new SchedulerSRTF();
                        break;
                    case 4:
                        filas[i] = new SchedulerRR();
                        ((SchedulerRR) (filas[i])).medium = 7;
                        break;
                    default:
                        b = -1;
                }
            } catch (Exception e) {
                in.nextLine();
                b = -1;
            }

        }

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
        else if (atual.interrupcao(tempo, falta)) {
           
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
