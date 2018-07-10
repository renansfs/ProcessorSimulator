/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processador;

import Memory.RAM;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import Scheduler.Element;
import Scheduler.Scheduler;

/**
 *
 *
 */
public class UC {

    Scheduler atual; //Escalonador Atual
    ULA processos;  //Unidade que processa ULA
    int geral = 1; //contador
    public int media = 0, elements = 0;
    File saida; //arquivo de saída
    FileOutputStream fos = null;
    GM gerenciador;

    public UC(Scheduler atual, File saida) throws IOException {
        this.atual = atual; //Recebe o escalonador que será utilizado
        this.saida = saida; //recebe o arquivo de saida
        fos = new FileOutputStream(saida);
        fos.write(("Number Event-------Event-------Next Event-------Event Time" + System.getProperty("line.separator")).getBytes());
    }

    public void iniciarULA(ULA nova) { //recebe a unidade de processamento na Unidade Central UC
        processos = nova;
    }

    public void iniciarGM(GM gerencia) { //recebe o gerenciador
        gerenciador = gerencia;
    }

    @SuppressWarnings("empty-statement")
    public void processar(int can) { //processa os elementos que são fornecidos pelo Escalonador
        int[] result = new int[2]; //array de resultados [0] verificado quanto falta do processo [1] tempo processado

        gerenciador.verificar(geral);

        while (true) {

            Element a = atual.getEvent(geral); //pega na lista o prox elemento

            if (a == null) { //se não existe mais elementos na fila
                if (gerenciador.verificar(geral) == -1) {
                    try {
                        fos.write(("Fim dos processos. Tempo medio: " + espera(can)).getBytes()); //imprimi o tempo medio
                    } catch (FileNotFoundException e) {
                        System.err.println("Arquivo não encontrado");
                    } catch (IOException e) {
                        System.err.println("Erro do arquivo");
                    } finally {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            System.err.println("Erro ao fechar arquivo");
                        }
                    }
                    break;
                } else {
                    geral++;
                    while(gerenciador.verificar(geral) != 0 && gerenciador.verificar(geral) != -1);            
                }

            } else if (a.chegada > geral) { //volta para a fila, caso ainda nao deu tempo de chegar 
                atual.addEvent(a.chegada, a.timeburst, a.nome, a.pri, a.job, a.getCM());
            } else {
                elements++;
                result = processos.processar(a, geral, gerenciador); //envia os eventos para processamento
                if (result[1] > 0) { //caso houve interrupcao o elemento volta para o lugar devido
                    geral = result[0]; //acrescenta o tempo que foi processado ao tempo geral
                    atual.addEvent(geral, result[1], a.nome, a.pri, a.job, a.getCM()); //vai para o lugar devido
                    gravar(a);

                } else { //fim completo de um processo
                    geral = result[0];
                    gerenciador.liberar(a.cm);
                    gravar(a);
                }
              //  geral++;
            }

        }
    }

    /*
     * Gravacao no arquivo Processado.txt
     */
    void gravar(Element gravar) {

        int a = Math.abs(gravar.entrada - gravar.chegada); //inicio - chegada
        media = media + a;

        try {
            fos.write((("    " + gravar.job + "               " + gravar.nome + "             " + atual.head() + "                 " + gravar.entrada) + System.getProperty("line.separator")).getBytes());
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.err.println("Erro de arquivo");
        }

    }

    double espera(int can) { //retorno do valor de espera media 
        return (media / elements);
    }

}
