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
import model.Cidade;
import util.Db;

/**
 *
 * @author natan
 */
public class CidadeDao {

    // Atributo para armazenar a conexao com o banco de dados
    private Connection conexao;

    // Construtor busca uma conexão ao banco na respectiva classe
    public CidadeDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    /**
     * Método para incluir uma nova cidade
     *
     * @param cidade
     */
    public int IdCidade() throws SQLException {
        String sql = "select max(idcidade+1) from cidade";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        return 0;
    }

    public void insertCidade(Cidade cidade) {
        try {
            String sql = "INSERT INTO cidade( nome, estado_idestado, disponibilidade) VALUES ( ?, ?, true )";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setString(2, cidade.getEstado_idestado());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para apagar uma cidade
     *
     * @param codigoCidade
     */
    public void deleteCidade(int codigoCidade) {
        try {
            String sql = "UPDATE cidade SET disponibilidade=false WHERE idcidade=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoCidade);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar os dados de uma cidade
     *
     * @param cidade
     */
    public void updateCidade(Cidade cidade) {
        try {
            String sql = "UPDATE cidade SET nome=?, estado_idestado=? WHERE idcidade=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setString(2, cidade.getEstado_idestado());
            preparedStatement.setInt(3, cidade.getIdcidade());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para retornar todas as cidades cadastradas
     *
     * @return
     */
    public List<Cidade> getAllCidades() {
        List<Cidade> cidades = new ArrayList<Cidade>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cidade WHERE disponibilidade = true ORDER BY idcidade");

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado_idestado(rs.getString("estado_idestado"));

                // Adicionar à lista
                cidades.add(cidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retornar a lista de cidades
        return cidades;
    }

    /**
     * Retornar os dados de uma cidade
     *
     * @param codigoCidade
     * @return
     */
    public Cidade getCidadeByCodigo(int codigoCidade) {

        // Instanciar objeto que será retornado
        Cidade cidade = new Cidade();

        try {
            String sql = "SELECT * FROM cidade WHERE idcidade=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoCidade);

            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto cidade
            if (rs.next()) {
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado_idestado(rs.getString("estado_idestado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar objeto com os dados
        return cidade;
    }

}
