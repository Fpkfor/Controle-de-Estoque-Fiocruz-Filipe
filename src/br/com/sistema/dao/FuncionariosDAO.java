/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Funcionarios;
import br.com.sistema.view.AreaTrabalho;
import br.com.sistema.view.FormularioLogin;
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
public class FuncionariosDAO {
    private Connection conn;
    
    public FuncionariosDAO(){
        this.conn = new ConexaoBanco() .pegarConexao();  
    }
    
    public void Salvar(Funcionarios obj){
        try {
            //Criar SQL
            String sql="insert into tb_funcionarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //Preparar conexão com Banco de dados SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8,obj.getTelefone());
            stmt.setString(9,obj.getCelular());
            stmt.setString(10,obj.getCep());
            stmt.setString(11,obj.getEndereco());
            stmt.setInt(12,obj.getNumero());
            stmt.setString(13,obj.getComplemento());
            stmt.setString(14,obj.getBairro());
            stmt.setString(15,obj.getCidade());
            stmt.setString(16,obj.getEstado());
            
            // Iniciar a conexão SQL
            stmt.execute();
            // Terminar a conexão SQL
            stmt.close();
            JOptionPane.showMessageDialog(null, "O funcionário foi salvo com sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao salvar ao funcionário"+ erro);
        }
    }
    
    public void Editar(Funcionarios obj){
        try {
            //Editar o que está no SQL
            String sql="update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=? ";
            //Preparar conexão com Banco de dados SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8,obj.getTelefone());
            stmt.setString(9,obj.getCelular());
            stmt.setString(10,obj.getCep());
            stmt.setString(11,obj.getEndereco());
            stmt.setInt(12,obj.getNumero());
            stmt.setString(13,obj.getComplemento());
            stmt.setString(14,obj.getBairro());
            stmt.setString(15,obj.getCidade());
            stmt.setString(16,obj.getEstado());
            stmt.setInt(17,obj.getId());
            
            // Iniciar a conexão SQL
            stmt.execute();
            // Terminar a conexão SQL
            stmt.close();
            JOptionPane.showMessageDialog(null, "O funcionário foi editado com sucesso.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Houve um erro ao editar ao funcionário"+ erro);
        }
    }
    
    
    public void Excluir(Funcionarios obj){
        try {
            String sql = "delete from tb_funcionarios where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "O funcionário foi excluído com sucesso");
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Houve um erro ao tentar excluir o funcionário"+ e);
        }
    }
    
    
    
    
    public Funcionarios BuscarFuncionario(String nome){
        try {
            String sql = "select * from tb_funcionarios where nome=?";
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs= stmt.executeQuery();
            Funcionarios obj = new Funcionarios ();
            if(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            JOptionPane.showMessageDialog(null, "Houve um erro ao tentar encontrar o funcionário" + erro);
        }
        return null;
    }
    public List<Funcionarios>Listar(){
    List<Funcionarios> lista = new ArrayList<>();
        try {
            //A seguir um string sql para escolher a tabela
            //Um preparedstatement para fazer a conexao com a tabela
            //Um Resultset para percorrer a tabela linha por linha até ler tudo que está salvo
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
               obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    public List<Funcionarios>Filtrar(String nome){
    List<Funcionarios> lista = new ArrayList<>();
        try {
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
               obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    
    //método para efetuar login no formulario login
    public void EfetuarLogin(String email,String senha){
        try {
            String sql = "select * from tb_funcionarios where email=? and senha=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getString("nivel_acesso").equals("Adminstrador")){
                AreaTrabalho at = new AreaTrabalho();
                at.usuarioLogado = rs.getString("nome");
                JOptionPane.showMessageDialog(null,"Bem-vindo ao sistema!\n"+at.usuarioLogado);
                at.setVisible(true);
                }
                else if(rs.getString("nivel_acesso").equals("Usuário")){
                AreaTrabalho at = new AreaTrabalho();
                at.usuarioLogado = rs.getString("nome");
                at.menu_fornecedores.setVisible(false);
                at.menu_funcionario.setEnabled(false);
                at.menu_estoque.setEnabled(false);               
                JOptionPane.showMessageDialog(null,"Bem-vindo ao sistema!\n"+at.usuarioLogado);
                at.setVisible(true);
                }
            } else{
                FormularioLogin login = new FormularioLogin();
                JOptionPane.showMessageDialog(null,"Dados inválidos, verifique se você digitou algo errado e tente novamente" );
                login.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro"+e);
        }
        
    }
}