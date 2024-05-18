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
public class Produtos {
    private int id;
    private String descricao;
    private double preco;
    private int qtd_estoque;
    //a parte de fornecedores na tabela produtos, é uma chava estrangeira, por isso:
    private Fornecedores fornecedores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Fornecedores getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedores fornecedores) {
        this.fornecedores = fornecedores;
    }

    public int getQtd_Estoque() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFornecedor(Fornecedores fornecedores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
