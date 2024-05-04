/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
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
public class ClientesDAO {
    private Connection conn;
    
    public ClientesDAO(){
        this.conn = new ConexaoBanco() .pegarConexao();  
    }
    
    public void Salvar(Clientes obj){
        try {
            //Criar SQL
            String sql="insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //Preparar conexão com Banco de dados SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
            
            // Iniciar a conexão SQL
            stmt.execute();
            // Terminar a conexão SQL
            stmt.close();
            JOptionPane.showMessageDialog(null, "O cliente foi salvo com sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao salvar ao cliente"+ erro);
        }
    }
    
    public void Editar(Clientes obj){
        try {
            //Editar o que está no SQL
            String sql="update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=? ";
            //Preparar conexão com Banco de dados SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
            stmt.setInt(14,obj.getId());
            
            // Iniciar a conexão SQL
            stmt.execute();
            // Terminar a conexão SQL
            stmt.close();
            JOptionPane.showMessageDialog(null, "O cliente foi editado com sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao editar ao cliente"+ erro);
        }
    }
    
    
    public void Excluir(Clientes obj){
        try {
            String sql = "delete from tb_clientes where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "O cliente foi excluído com sucesso");
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Houve um erro ao tentar excluir o cliente"+ e);
        }
    }
    
    
    
    
    public Clientes BuscarCliente(String nome){
        try {
            String sql = "select * from tb_clientes where nome=?";
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs= stmt.executeQuery();
            Clientes obj = new Clientes ();
            if(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));   
            }
            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar encontrar o cliente" + erro);
        }
        return null;
    }
    public List<Clientes>Listar(){
    List<Clientes> lista = new ArrayList<>();
        try {
            //A seguir um string sql para escolher a tabela
            //Um preparedstatement para fazer a conexao com a tabela
            //Um Resultset para percorrer a tabela linha por linha até ler tudo que está salvo
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
               obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao criar a lista");
        }
        return null;
    }
    
    public List<Clientes>Filtrar(String nome){
    List<Clientes> lista = new ArrayList<>();
        try {
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
               obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao criar a lista");
        }
        return null;
    }
}