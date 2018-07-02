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
import model.Pais;
import util.Db;

/**
 *
 * @author natan
 */
public class PaisDao {

    // Atributo para armazenar a conexao com o banco de dados
    private Connection conexao;

    // Construtor busca uma conexão ao banco na respectiva classe
    public PaisDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    /**
     * Método para incluir uma nova pais
     *
     * @param pais
     */
    public int IdPais() throws SQLException {
        String sql = "select max(idpais+1) from pais";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        return 0;
    }

    public void insertPais(Pais pais) {
        try {
            String sql = "INSERT INTO pais( nome, disponibilidade) VALUES ( ?, true )";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, pais.getNome());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para apagar uma pais
     *
     * @param codigoPais
     */
    public void deletePais(int codigoPais) {
        try {
            String sql = "UPDATE pais SET disponibilidade=false WHERE idpais=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoPais);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar os dados de uma pais
     *
     * @param pais
     */
    public void updatePais(Pais pais) {
        try {
            String sql = "UPDATE pais SET nome=? WHERE idpais=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, pais.getNome());
            preparedStatement.setInt(2, pais.getIdpais());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para retornar todas as paises cadastradas
     *
     * @return
     */
    public List<Pais> getAllPaises() {
        List<Pais> paises = new ArrayList<Pais>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM pais WHERE disponibilidade = true ORDER BY idpais");

            while (rs.next()) {
                Pais pais1 = new Pais();
                pais1.setIdpais(rs.getInt("idpais"));
                pais1.setNome(rs.getString("nome"));

                // Adicionar à lista
                paises.add(pais1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retornar a lista de paises
        return paises;
    }

    /**
     * Retornar os dados de uma pais
     *
     * @param codigoPais
     * @return
     */
    public Pais getPaisByCodigo(int codigoPais) {

        // Instanciar objeto que será retornado
        Pais paises = new Pais();

        try {
            String sql = "SELECT * FROM pais WHERE idpais=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoPais);
            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto pais
            if (rs.next()) {
                paises.setIdpais(rs.getInt("idpais"));
                paises.setNome(rs.getString("nome"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar objeto com os dados
        return paises;
    }

}
