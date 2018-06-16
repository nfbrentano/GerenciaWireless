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
import model.Estado;
import util.Db;

/**
 *
 * @author natan
 */
public class EstadoDao {
    
    // Atributo para armazenar a conexao com o banco de dados
    private Connection conexao;

    // Construtor busca uma conexão ao banco na respectiva classe
    public EstadoDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    /**
     * Método para incluir uma nova estado
     *
     * @param estado
     */
    public void insertEstado(Estado estado) {
        try {
            String sql = "INSERT INTO estado(nome, pais_idpais, disponibilidade) VALUES ( ?, ?, true )";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, estado.getNome());
            preparedStatement.setString(2, estado.getPais_idpais());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para apagar uma estado
     *
     * @param codigoEstado
     */
    public void deleteEstado(int codigoEstado) {
        try {
            String sql = "UPDATE estado SET disponibilidade=false WHERE idestado=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoEstado);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar os dados de uma estado
     * @param estado 
     */
    public void updateEstado(Estado estado) {
        try {
            String sql = "UPDATE estado SET nome=?, pais_idpais=? WHERE idestado=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, estado.getNome());
            preparedStatement.setString(2, estado.getPais_idpais());
            preparedStatement.setInt(3, estado.getIdestado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para retornar todas as estados cadastradas
     * @return 
     */
    public List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<Estado>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM estado WHERE disponibilidade = true ORDER BY idestado DESC" );

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setPais_idpais(rs.getString("pais_idpais"));

                // Adicionar à lista
                estados.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retornar a lista de estados
        return estados;
    }

    /**
     * Retornar os dados de uma estado
     * @param codigoEstado
     * @return 
     */
    public Estado getEstadoByCodigo(int codigoEstado) {

        // Instanciar objeto que será retornado
        Estado estado = new Estado();

        try {
            String sql = "SELECT * FROM estado WHERE idestado=?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoEstado);
            
            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto estado
            if (rs.next()) {
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setPais_idpais(rs.getString("pais_idpais"));                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar objeto com os dados
        return estado;
    }

}

