/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Endereco;
import util.Db;

/**
 *
 * @author natan
 */
public class EnderecoDao {

    // Atributo para armazenar a conexao com o banco de dados
    private Connection conexao;

    // Construtor busca uma conexão ao banco na respectiva classe
    public EnderecoDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    /**
     * Método para incluir uma nova endereco
     *
     * @param endereco
     */
    public int IdEndereco () throws SQLException{
        String sql = "select max(idendereco+1) from endereco";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        return 0;
    }
    
    public void insertEndereco(Endereco endereco) {
        try {
            String sql = "INSERT INTO endereco( rua, bairro_idbairro, disponibilidade) VALUES ( ?, ?, true )";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getBairro_idbairro());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para apagar uma endereco
     *
     * @param codigoEndereco
     */
    public void deleteEndereco(int codigoEndereco) {
        try {
            String sql = "UPDATE endereco SET disponibilidade=false WHERE idendereco=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoEndereco);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar os dados de uma endereco
     * @param endereco 
     */
    public void updateEndereco(Endereco endereco) {
        try {
            String sql = "UPDATE endereco SET rua=?, bairro_idbairro=? WHERE idendereco=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getBairro_idbairro());
            preparedStatement.setInt(3, endereco.getIdendereco());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para retornar todas as enderecos cadastradas
     * @return 
     */
    public List<Endereco> getAllEnderecos() {
        List<Endereco> enderecos = new ArrayList<Endereco>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM endereco WHERE disponibilidade = true ORDER BY idendereco");

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdendereco(rs.getInt("idendereco"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro_idbairro(rs.getString("bairro_idbairro"));

                // Adicionar à lista
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retornar a lista de enderecos
        return enderecos;
    }

    /**
     * Retornar os dados de uma endereco
     * @param codigoEndereco
     * @return 
     */
    public Endereco getEnderecoByCodigo(int codigoEndereco) {

        // Instanciar objeto que será retornado
        Endereco endereco = new Endereco();

        try {
            String sql = "SELECT * FROM endereco WHERE idendereco=?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoEndereco);
            
            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto endereco
            if (rs.next()) {
                endereco.setIdendereco(rs.getInt("idendereco"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro_idbairro(rs.getString("bairro_idbairro"));                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar objeto com os dados
        return endereco;
    }

}