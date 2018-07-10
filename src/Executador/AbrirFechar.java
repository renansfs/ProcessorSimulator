/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Executador;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Galaxy
 */
public class AbrirFechar extends JFrame {
    
    JLabel label1 = new JLabel("Selecione um Arquivo de entrada '.csv': ");
    JLabel label3;
    JLabel label4;
    JLabel label2 = new JLabel("Selecione um Diretório de saída: ");
    
    JFileChooser fileChooser;    
    int retorno;
    File file;
    File diretorio;
    String nome;
    Container pane = this.getContentPane();
    
    /*
     * Abre o arquivo csv.
     */
    
    public void Abrir() {
        pane.setLayout(new GridLayout(4, 2)); //define o layout do painel     
        this.setSize(600, 400);
        this.setVisible(true);
                
        
            try {        
                pane.add(label1);
              
                fileChooser = new JFileChooser();
                retorno = fileChooser.showOpenDialog(null);
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    label3 = new JLabel("Arquivo de entrada: " + file.getAbsolutePath());
                    pane.add(label3);
                } else {
                    throw new IOException();
                }                
            } catch (IOException e) {
               JOptionPane.showMessageDialog(null, "Abortando Aplicativo!");
               System.exit(0);
            }
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
        
    }
/*
     * Escolhe o diretorio do arquivo csv.
     */
    
    public void gravarArquivo(){
      pane.add(label2);
        pane.setLayout(new GridLayout(4, 2)); //define o layout do painel     
        this.setSize(600, 400);
        this.setVisible(true);
                
        PrintWriter print = null;
            try {        
                pane.add(label1);
              
                JFileChooser fc = new JFileChooser();
                    // restringe a amostra a diretorios apenas
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    
                    int res = fc.showOpenDialog(null);
                    
                    if(res == JFileChooser.APPROVE_OPTION){
                        
                        diretorio = fc.getSelectedFile();            
                        diretorio = new File(diretorio.getAbsolutePath() +"\\Processado.txt");
                                       
                            diretorio.createNewFile();
                           
                        
                        label3 = new JLabel("Saída: " + diretorio.getAbsolutePath());
                        pane.add(label3);
                    }
                    else{
          
                    throw new IOException();
                }                
            } catch (IOException e) {
               JOptionPane.showMessageDialog(null, "Abortando Aplicativo!");
               System.exit(0);
            }
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
          
    }
    
    
    public File arquivo() {
        return null;
    }
}
