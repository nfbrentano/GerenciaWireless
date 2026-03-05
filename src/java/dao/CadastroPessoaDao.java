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

public class CadastroPessoaDao {

    public CadastroPessoaDao() {
    }

    public void insertCadastroPessoa(CadastroPessoa cadastroPessoa) {
        String sql = "INSERT INTO cadastroPessoa(nome, sobrenome, documento, pais, estado, cidade, bairro, endereco, numeroendereco, nomeusuario, senhaacesso, pontoacesso, disponibilidade) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true)";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

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
            preparedStatement.setString(12, cadastroPessoa.getPontoacesso());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCadastroPessoa(int codigoCadastroPessoa) {
        String sql = "UPDATE cadastroPessoa SET disponibilidade = false WHERE idcadastroPessoa=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoCadastroPessoa);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCadastroPessoa(CadastroPessoa cadastroPessoa) {
        String sql = "UPDATE cadastroPessoa SET nome=?, sobrenome=?, documento=?, pais=?, estado=?, cidade=?, bairro=?, endereco=?, numeroendereco=?, nomeusuario=?, senhaacesso=?, pontoacesso=? WHERE idcadastroPessoa=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

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
            preparedStatement.setString(12, cadastroPessoa.getPontoacesso());
            preparedStatement.setInt(13, cadastroPessoa.getIdcadastroPessoa());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CadastroPessoa> getAllCadastroPessoa() {
        List<CadastroPessoa> cadastroPessoas = new ArrayList<>();
        String sql = "SELECT * FROM cadastroPessoa WHERE disponibilidade = true ORDER BY nome";
        try (Connection conexao = Db.getConexao();
             Statement statement = conexao.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

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
                pessoa.setPontoacesso(rs.getString("pontoacesso"));
                cadastroPessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cadastroPessoas;
    }

    public CadastroPessoa getCadastroPessoaByCodigo(int codigoCadastroPessoa) {
        CadastroPessoa pessoa = null;
        String sql = "SELECT * FROM cadastroPessoa WHERE idcadastroPessoa=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoCadastroPessoa);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pessoa = new CadastroPessoa();
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
                    pessoa.setPontoacesso(rs.getString("pontoacesso"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }
}
