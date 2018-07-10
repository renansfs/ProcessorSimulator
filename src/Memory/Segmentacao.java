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
 * @author Renan
 */
public class Segmentacao implements RAM {
    private Element[] fisica = new Element[65536]; //ponteiro para o job
    int espaco;
    
    @Override
    public int getTamanho() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEscalonador(Scheduler escalonador){
    
    }
    public boolean enviarProcesso(int tempo){
        return true;
    }
    
    
    public void liberar(int[] espaco){
    
    }
    
    
 
    public void add(int chegada, int tamanho, String nome, int prioridade, int job) {
        int[] a = new int[2];
        Element atual = new Element(nome, chegada, tamanho, job, a); //cria um novo processo
        if(espaco>=tamanho){
            for(int i=0; i<fisica.length; i++){
            
                
            }
        }
            
        
        }//adiciona a memoriA

    @Override
    public boolean enviarProcesso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Element aux, int tam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarBlocos(int tamanho) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
