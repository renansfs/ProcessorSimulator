/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Executador;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Galaxy
 */
public class Executador {

    Selecionador novo = new Selecionador();
    AbrirFechar sim = new AbrirFechar();

    public static void main(String[] args) throws IOException {
        Executador apply = new Executador(); //Cria um objeto aplicativo
        apply.executar(); //executa o objeto aplicativo

    }

        
    void executar() throws IOException {

        sim.Abrir();
        sim.gravarArquivo();
        novo.arqEntrada(sim.file);
        novo.arqSaida(sim.diretorio);
        sim.dispose();
        escolherEscalonador(); //Menu de escolha do escalonador

    }

    void escolherEscalonador() throws IOException {

        int b = -1;
        Scanner in = new Scanner(System.in);
        while (b == -1) {
            try {
                
                System.out.println("\n Digite o numero do algoritmo de sua preferencia: ");
                System.out.println(" 1- FCFS \n 2- SJF \n 3-SRTF \n 4-RR \n 5- MF \n 6- MFR");

                b = in.nextInt();
                switch (b) {
                    case 1:
                        novo.FCFS();
                        JOptionPane.showMessageDialog(null, "FCFS processado, verifique o arquivo Processado.txt na pasta de saida");
                        System.exit(0);
                    case 2:
                        novo.SJF();
                        JOptionPane.showMessageDialog(null, "SJF processado, verifique o arquivo Processado.txt na pasta de saida");
                        System.exit(0);
                    case 3:
                        novo.SRTF();
                        JOptionPane.showMessageDialog(null, "SRTF processado, verifique o arquivo Processado.txt na pasta de saida");
                        System.exit(0);
                    case 4:
                        novo.RR();
                        JOptionPane.showMessageDialog(null, "RR processado, verifique o arquivo Processado.txt na pasta de saida");
                        System.exit(0);
                    case 5:
                        novo.MF();
                        JOptionPane.showMessageDialog(null, "MF processado, verifique o arquivo Processado.txt na pasta de saida");
                        System.exit(0);
                    case 6:
                        novo.MFR();
                        JOptionPane.showMessageDialog(null, "MFR processado, verifique o arquivo Processado.txt na pasta de saida");
                        System.exit(0);
                    default:
                        for(int a = 0; a< 100; a++) System.out.println("\n");
                        b = -1;
                }
            } catch (IOException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Erro de acesso ao arquivo");
                in.nextLine();
                b = -1;
                

            } catch (InputMismatchException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Digito invalido, tente novamente.");
                in.nextLine();
                b = -1;
                
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Arquivo de entrada com formato incorreto, execute o programa novamente");
                System.exit(0);
            } catch (ArithmeticException e){
            
                System.out.println(e);
                 JOptionPane.showMessageDialog(null, "Arquivo não contém processos");
                 System.exit(0);
               
            }/*catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Erro desconhecido, tente novamente.");
                in.nextLine();
                
            }
*/
        }
    }
}
