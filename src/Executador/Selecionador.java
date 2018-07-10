
package Executador;


import java.io.*;
import java.util.Scanner;
import Memory.*;
import Processador.*;
import Scheduler.*;
import Lista.*;
/**
 *
 * @author Galaxy
 */
public class Selecionador {
    //escalonadores
    static RAM MemoryPag = new Paginacao();
    static GM gerenciador;
    static Lista Eventlist = new Lista();
    static Scheduler FCFS = new SchedulerFCFS();
    static Scheduler SJF = new SchedulerSJF();
    static Scheduler SRTF = new SchedulerSRTF();
    static Scheduler RR = new SchedulerRR();
    static Scheduler MF = new SchedulerMF();
    static Scheduler MFR = new SchedulerMFR();
    static File file, saida;
    
    //recebe o arquivo de entrada
    public void arqEntrada(File file){
         this.file = file;
    }
   
    //recebe o arquivo de saidar
     public void arqSaida(File file){
        saida = file;
    }

     void lista(File cf, int control) throws IOException { //deverá receber a memória, ao invês do escalonador
         int [] prov = {0,0};
//A estrutura try-catch é usada pois o objeto BufferedWriter exige que as
//excessões sejam tratadas
        try {
            FileReader agora = new FileReader(cf); //abre arquivo de entrada
            try (BufferedReader StrR = new BufferedReader(agora)) {
                String Str;
                String[] TableLine;

                //Essa estrutura do looping while é clássica para ler cada linha
                //do arquivo
                int burst=0, chegada =0, saida=0, tamanho=0, prioridade=0, job=0;
                String id = null;
                int a = 1;
                int b = 0;
                while ((Str = StrR.readLine()) != null) {
                    //Aqui usamos o método split que divide a linha lida em um array de String
                    //passando como parametro o divisor ";".
                    TableLine = Str.split(";");

                    //O foreach é usadao para imprimir cada célula do array de String.
                    for (String cell : TableLine) {
                        
                        switch (a) {
                            case 1:
                                id = cell;
                                break;
                            case 2:
                                chegada = Integer.parseInt(cell);
                                break;
                            case 3:
                                tamanho = Integer.parseInt(cell);
                                break;
                            case 4:
                                prioridade = Integer.parseInt(cell);
                                break;
                        }
                        a++;
                    }
                    job++; //alterar aqui para enviar para memória, ao invés de enviar para o escalonador               
                    Eventlist.add(chegada, tamanho, id, prioridade, job, prov); //adiciona a memoria de paginacao               
                    a = 1;
                    b++;
                }
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, abortando!");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Arquivo com erro, abortando!");
            System.exit(0);
        }

    }

   //execução dos escalonadores
    public int Memoria(){
        Scanner in = new Scanner(System.in);
        int b = 0;
        System.out.println("\n Escolha o Sistema de Memória: \n 1- Paginacao \n 2 - Segmentacao");
        b = in.nextInt();
        while(true) if(b == 1 || b == 2) break;
        return b;
    }
     
    public void FCFS() throws IOException {
        System.out.println("Executando FCFS \n");
        
        int b = Memoria();
        lista(file, b);
        UC unidade = new UC(FCFS, saida);
        
        if(b == 1){ //Executa a Paginacao
            unidade.iniciarGM(gerenciador);
            MemoryPag.setEscalonador(FCFS);
            gerenciador = new GM(Eventlist, MemoryPag);
            unidade.iniciarGM(gerenciador);
        }
        else{ //executa a Segmentacao
           
          //  MemorySeg.setEscalonador(FCFS);
        }
       
        ULA processador = new ULA(unidade);
        unidade.iniciarULA(processador);
        unidade.processar(0);
    }

    public void SJF() throws IOException {
        System.out.println( "Executando SJF");
        int b = Memoria();
        lista(file, b);
        UC unidade = new UC(SJF,saida);
        
        if(b == 1){ //Executa a Paginacao
            unidade.iniciarGM(gerenciador);
            MemoryPag.setEscalonador(SJF);
            gerenciador = new GM(Eventlist, MemoryPag);
            unidade.iniciarGM(gerenciador);
        }
        else{ //executa a Segmentacao
            
         //   MemorySeg.setEscalonador(SJF);
        }
        ULA processador = new ULA(unidade);
        unidade.iniciarULA(processador);
        unidade.processar(1);
    }

    public void SRTF() throws IOException {
        System.out.println( "Executando SRTF");
        int b = Memoria();
        lista(file, b);
        UC unidade = new UC(SRTF,saida);
        ULA processador = new ULA(unidade);
        
        if(b == 1){ //Executa a Paginacao
            unidade.iniciarGM(gerenciador);
            MemoryPag.setEscalonador(SRTF);
            gerenciador = new GM(Eventlist, MemoryPag);
            unidade.iniciarGM(gerenciador);
        }
        else{ //executa a Segmentacao
            
          //  MemorySeg.setEscalonador(SRTF);
        }
        unidade.iniciarULA(processador);
        unidade.processar(2);
    }

    public void RR() throws IOException {
        System.out.println( "Executando RR");
        int b = Memoria();
        lista(file, b);
        UC unidade = new UC(RR,saida);
        
        if(b == 1){ //Executa a Paginacao
            unidade.iniciarGM(gerenciador);
            MemoryPag.setEscalonador(RR);
            gerenciador = new GM(Eventlist, MemoryPag);
            unidade.iniciarGM(gerenciador);
        }
        else{ //executa a Segmentacao
          
          //  MemorySeg.setEscalonador(RR);
        }
        
        ULA processador = new ULA(unidade);
        unidade.iniciarULA(processador);
        ((SchedulerRR) RR).total();
        unidade.processar(3);
    }
    public void MF() throws IOException {
        System.out.println("Executando MF");
        int b = 0;
        Scanner in = new Scanner(System.in);
        while(b<1){
        try{
            System.out.println("Digite a quantidade de filas");
            b = in.nextInt();
        ((SchedulerMF)MF).numeroFilas(b);
        }catch(Exception e){
            in.nextLine();
            }
        }
        
        
        b = Memoria();
        lista(file, b);
        UC unidade = new UC(MF, saida);
           
        if(b == 1){ //Executa a Paginacao
            unidade.iniciarGM(gerenciador);
            MemoryPag.setEscalonador(MF);
            gerenciador = new GM(Eventlist, MemoryPag);
            unidade.iniciarGM(gerenciador);
        }
        else{ //executa a Segmentacao
           
         //   MemorySeg.setEscalonador(MF);
        }
        
        ULA processador = new ULA(unidade);
        unidade.iniciarULA(processador);
        unidade.processar(4);
    }
    public void MFR() throws IOException {
        System.out.println("Executando MFR");  
        int b = Memoria();
        ((SchedulerMFR)MFR).numeroFilas();
       
        lista(file, b);
        UC unidade = new UC(MFR, saida);
        
        if(b == 1){ //Executa a Paginacao
           unidade.iniciarGM(gerenciador);
            MemoryPag.setEscalonador(MFR);
            gerenciador = new GM(Eventlist, MemoryPag);
            unidade.iniciarGM(gerenciador);
        }
        else{ //executa a Segmentacao
           
        //    MemorySeg.setEscalonador(FCFS);
        }
        
        ULA processador = new ULA(unidade);
        unidade.iniciarULA(processador);
        unidade.processar(5);
        
    }
    
}
