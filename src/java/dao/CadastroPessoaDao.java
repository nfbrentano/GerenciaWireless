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
import model.CadastroPessoa;

import util.Db;

/**
 *
 * @author natan
 */
public class CadastroPessoaDao {

    // Atributo para armazenar a conexao com o banco de dados
    private Connection conexao;

    // Construtor busca uma conexão ao banco na respectiva classe
    public CadastroPessoaDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    /**
     * Método para incluir um novo cadastroPessoa
     *
     * @param cadastroPessoa
     */
    public void insertCadastroPessoa(CadastroPessoa cadastroPessoa) {
        try {
            String sql = "INSERT INTO cadastroPessoa(nome, sobrenome, documento, pais, estado, cidade, bairro, endereco, numeroendereco, nomeusuario, senhaacesso, disponibilidade) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true)";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, cadastroPessoa.getNome());
            preparedStatement.setString(2, cadastroPessoa.getSobrenome());
            preparedStatement.setString(3, cadastroPessoa.getDocumento());
            preparedStatement.setString(4, cadastroPessoa.getPais());
            preparedStatement.setString(5, cadastroPessoa.getEstado());
            preparedStatement.setString(6, cadastroPessoa.getCidade());
            preparedStatement.setString(7, cadastroPessoa.getBairro());
            preparedStatement.setString(8, cadastroPessoa.getEndereco());
            preparedStatement.setString(9, cadastroPessoa.getNumeroendereco());
            preparedStatement.setString(10, cadastroPessoa.getNomeusuario());
            preparedStatement.setString(11, cadastroPessoa.getSenhaacesso());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para apagar uma cadastroPessoa
     *
     * @param codigoCadastroPessoa
     */
    public void deleteCadastroPessoa(int codigoCadastroPessoa) {
        try {
            String sql = "UPDATE cadastroPessoa SET disponibilidade = false WHERE idcadastroPessoa=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoCadastroPessoa);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar os dados de uma cadastroPessoa
     *
     * @param cadastroPessoa
     */
    public void updateCadastroPessoa(CadastroPessoa cadastroPessoa) {
        try {
            String sql = "UPDATE cadastroPessoa SET nome=?, sobrenome=?, documento=?, pais=?, estado=?, cidade=?, bairro=?, endereco=?, numeroendereco=?, nomeusuario=?, senhaacesso=? WHERE idcadastroPessoa=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, cadastroPessoa.getNome());
            preparedStatement.setString(2, cadastroPessoa.getSobrenome());
            preparedStatement.setString(3, cadastroPessoa.getDocumento());
            preparedStatement.setString(4, cadastroPessoa.getPais());
            preparedStatement.setString(5, cadastroPessoa.getEstado());
            preparedStatement.setString(6, cadastroPessoa.getCidade());
            preparedStatement.setString(7, cadastroPessoa.getBairro());
            preparedStatement.setString(8, cadastroPessoa.getEndereco());
            preparedStatement.setString(9, cadastroPessoa.getNumeroendereco());
            preparedStatement.setString(10, cadastroPessoa.getNomeusuario());
            preparedStatement.setString(11, cadastroPessoa.getSenhaacesso());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para retornar todas as cadastroPessoas cadastradas
     *
     * @return
     */
    public List<CadastroPessoa> getAllCadastroPessoa() {
        List<CadastroPessoa> cadastroPessoa = new ArrayList<CadastroPessoa>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cadastroPessoa");

            while (rs.next()) {
                CadastroPessoa pessoa = new CadastroPessoa();
                pessoa.setIdcadastroPessoa(rs.getInt("idcadastroPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setSobrenome(rs.getString("sobrenome"));
                pessoa.setDocumento(rs.getString("documento"));
                pessoa.setPais(rs.getString("pais"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setNumeroendereco(rs.getString("numeroendereco"));
                pessoa.setNomeusuario(rs.getString("nomeusuario"));
                pessoa.setSenhaacesso(rs.getString("senhaacesso"));
                

                // Adicionar à lista
                cadastroPessoa.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retornar a lista de cadastroPessoas
        return cadastroPessoa;
    }

    /**
     * Retornar os dados de um cadastroPessoa
     *
     * @param codigoCadastroPessoa
     * @return
     */
    public CadastroPessoa getCadastroPessoaByCodigo(int codigoCadastroPessoa) {

        // Instanciar objeto que será retornado
        CadastroPessoa cadastroPessoa = new CadastroPessoa();

        try {
            String sql = "SELECT * FROM cadastroPessoa WHERE idcadastroPessoa=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoCadastroPessoa);

            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto cadastroPessoa
            if (rs.next()) {
                cadastroPessoa.setIdcadastroPessoa(rs.getInt("idcadastroPessoa"));
                cadastroPessoa.setNome(rs.getString("nome"));
                cadastroPessoa.setSobrenome(rs.getString("sobrenome"));
                cadastroPessoa.setDocumento(rs.getString("documento"));
                cadastroPessoa.setPais(rs.getString("pais"));
                cadastroPessoa.setEstado(rs.getString("estado"));
                cadastroPessoa.setCidade(rs.getString("cidade"));
                cadastroPessoa.setBairro(rs.getString("bairro"));
                cadastroPessoa.setEndereco(rs.getString("endereco"));
                cadastroPessoa.setNumeroendereco(rs.getString("numeroendereco"));
                cadastroPessoa.setNomeusuario(rs.getString("nomeusuario"));
                cadastroPessoa.setSenhaacesso(rs.getString("senhaacesso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar objeto com os dados
        return cadastroPessoa;
    }

}
