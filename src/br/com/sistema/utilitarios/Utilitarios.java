/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.utilitarios;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Pichau
 */

//O método LimparFormulario serve para apagar o formulário após uma adicão.
//Enquanto houver um elemento JTextField no formulário,ele vai excluir um por um
public class Utilitarios {
    public void LimparFormulario(JPanel container) {
        Component conponents[] = container.getComponents();
       for(Component component : conponents){
           if(component instanceof JTextField) {
               ((JTextField)component).setText(null);
            }
        }
    }

    public void LimparFormulario(JTabbedPane painel_guias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
