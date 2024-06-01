/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class ProdutosDAO {
    private Connection conn;
    
    public ProdutosDAO(){
        this.conn = new ConexaoBanco() .pegarConexao();  
    }
    
    public void Salvar(Produtos obj){
        try {
            //Criar SQL
            String sql="insert into tb_produtos (descricao,preco,qtd_estoque,for_id)"
                    + "values(?,?,?,?)";
            //Preparar conexão com Banco de dados SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getDescricao());
            stmt.setDouble(2,obj.getPreco());
            stmt.setInt(3,obj.getQtd_estoque());
            stmt.setInt(4,obj.getFornecedores().getId());          
            // Iniciar a conexão SQL
            stmt.execute();
            // Terminar a conexão SQL
            stmt.close();
            JOptionPane.showMessageDialog(null, "O produto foi salvo com sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao salvar o produto"+ erro);
        }
    }
    
    public void Editar(Produtos obj){
        try {
            //Editar o que está no SQL
            String sql="update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=?,where id=?";
            //Preparar conexão com Banco de dados SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getDescricao());
            stmt.setDouble(2,obj.getPreco());
            stmt.setInt(3,obj.getQtd_estoque());
            stmt.setInt(4,obj.getFornecedores().getId());
            stmt.setInt(5,obj.getId());

            // Iniciar a conexão SQL
            stmt.execute();
            // Terminar a conexão SQL
            stmt.close();
            JOptionPane.showMessageDialog(null, "O produto foi editado com sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao editar o produto"+ erro);
        }
    }
    
    
    public void Excluir(Produtos obj){
        try {
            String sql = "delete from tb_produtos where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "O produto foi excluído com sucesso");
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Houve um erro ao tentar excluir o produto"+ e);
        }
    }
    
    
    
    
    public Produtos BuscarProdutos(String nome){
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join"
                    + " tb_fornecedores as f on(p.for_id=f.id) where p.descricao=?";
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs= stmt.executeQuery();
            Produtos obj = new Produtos ();
            Fornecedores f = new Fornecedores();
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque")); 
                
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);

            }
            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar encontrar o produto" + erro);
        }
        return null;
    }
    
        public Produtos BuscarProdutosCodigo(int id){
        try {
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join"
                    + " tb_fornecedores as f on(p.for_id=f.id) where p.id=?";
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();
            Produtos obj = new Produtos ();
            Fornecedores f = new Fornecedores();
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque")); 
                
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);

            }
            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar encontrar o produto" + erro);
        }
        return null;
    }
    
    public List<Produtos>Listar(){
    List<Produtos> lista = new ArrayList<>();
        try {
            //A seguir um string sql para escolher a tabela
            //Um preparedstatement para fazer a conexao com a tabela
            //Um Resultset para percorrer a tabela linha por linha até ler tudo que está salvo
            //No string sql a seguir, estou colocando que o for_id da tabela de produtos é o mesmo  que o id da tabela de fornecedores
            String sql = "select p.id,p.descricao,p.preco, p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                    obj.setId(rs.getInt("p.id"));
                    obj.setDescricao(rs.getString("p.descricao"));
                    obj.setPreco(rs.getDouble("p.preco"));
                    obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                    f.setNome(rs.getString("f.nome"));
                    obj.setFornecedores(f);        
                    lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao criar a lista");
        }
        return null;
    }
    
    public List<Produtos>Filtrar(String nome){
    List<Produtos> lista = new ArrayList<>();
        try {
            //uso do metodo inner join para mesclar tabelas. Aqui nos pegamos o ID da tabela de fornecedores e igualamos ao ID da tabela de produtos 
            String sql = "select p.id,p.descricao,.p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.descricao like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao criar a lista");
        }
        return null;
    }
    
    public void adicionarEstoque(int id, int qtd_nova){
        try {
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,qtd_nova);
            stmt.setInt(2,id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"O produto foi adicionado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao adicionar o produto ao estoque" + e);
        }
    }
    
      public void baixaEstoque(int id, int qtd_nova){
        try {
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,qtd_nova);
            stmt.setInt(2,id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"O estoque foi atualizado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao dar baixa no estoque" + e);
        }
    }
    public int retornaQtdAtualEstoque(int id) {
        try {
            int qtd_atual_estoque = 0;
                    String sql = "Select qtd_estoque from tb_produtos where id=?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if(rs.next()) {
                        qtd_atual_estoque = (rs.getInt("qtd_estoque"));
                    }
                    return qtd_atual_estoque;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao retornar a quantidade atual do estoque!"+e);
        }
    }
    
    
    
}