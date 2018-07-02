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
import model.Bairro;
import util.Db;

/**
 *
 * @author natan
 */
public class BairroDao {

    // Atributo para armazenar a conexao com o banco de dados
    private Connection conexao;

    // Construtor busca uma conexão ao banco na respectiva classe
    public BairroDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    /**
     * Método para incluir uma nova bairro
     *
     * @param bairro
     */
    public int IdBairro() throws SQLException {
        String sql = "select max(idbairro+1) from bairro";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        return 0;
    }

    public void insertBairro(Bairro bairro) {
        try {
            String sql = "INSERT INTO bairro( nome, cidade_idcidade, disponibilidade) VALUES ( ?, ?, true )";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, bairro.getNome());
            preparedStatement.setString(2, bairro.getCidade_idcidade());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para apagar uma bairro
     *
     * @param codigoBairro
     */
    public void deleteBairro(int codigoBairro) {
        try {
            String sql = "UPDATE bairro SET disponibilidade=false WHERE idbairro=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoBairro);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar os dados de uma bairro
     *
     * @param bairro
     */
    public void updateBairro(Bairro bairro) {
        try {
            String sql = "UPDATE bairro SET nome=?, cidade_idcidade=? WHERE idbairro=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, bairro.getNome());
            preparedStatement.setString(2, bairro.getCidade_idcidade());
            preparedStatement.setInt(3, bairro.getIdbairro());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para retornar todos os bairros cadastradas
     *
     * @return
     */
    public List<Bairro> getAllBairros() {
        List<Bairro> bairros = new ArrayList<Bairro>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM bairro WHERE disponibilidade = true ORDER BY idbairro");

            while (rs.next()) {
                Bairro bairro = new Bairro();
                bairro.setIdbairro(rs.getInt("idbairro"));
                bairro.setNome(rs.getString("nome"));
                bairro.setCidade_idcidade(rs.getString("cidade_idcidade"));

                // Adicionar à lista
                bairros.add(bairro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retornar a lista de bairros
        return bairros;
    }

    /**
     * Retornar os dados de uma bairro
     *
     * @param codigoBairro
     * @return
     */
    public Bairro getBairroByCodigo(int codigoBairro) {

        // Instanciar objeto que será retornado
        Bairro bairro = new Bairro();

        try {
            String sql = "SELECT * FROM bairro WHERE idbairro=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoBairro);

            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto bairro
            if (rs.next()) {
                bairro.setIdbairro(rs.getInt("idbairro"));
                bairro.setNome(rs.getString("nome"));
                bairro.setCidade_idcidade(rs.getString("cidade_idcidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar objeto com os dados
        return bairro;
    }

}
