/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.jdbc;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class TestarConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new ConexaoBanco().pegarConexao();
            JOptionPane.showMessageDialog(null,"O banco de dados foi conectado com sucesso");
        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null,"Erro ao se conectar com o banco de dados:" + erro.getMessage());
        }
    }
    
}
