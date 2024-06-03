/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;

/**
 *
 * @author Pichau
 */
public class Fornecedores extends Clientes {
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    // Uso de sobrecarga para conseguirmos extrair o nome exato dos fornecedores para o formulário/guia de produtos
    @Override
    public String toString(){
        return this.getNome();
    }
    
}
