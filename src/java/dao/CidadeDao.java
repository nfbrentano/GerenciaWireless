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

public class CidadeDao {

    public CidadeDao() {
    }

    public void insertCidade(Cidade cidade) {
        String sql = "INSERT INTO cidade(nome, estado_idestado, disponibilidade) VALUES (?, ?, true)";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setString(2, cidade.getEstado_idestado());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCidade(int codigoCidade) {
        String sql = "UPDATE cidade SET disponibilidade=false WHERE idcidade=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoCidade);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCidade(Cidade cidade) {
        String sql = "UPDATE cidade SET nome=?, estado_idestado=? WHERE idcidade=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setString(2, cidade.getEstado_idestado());
            preparedStatement.setInt(3, cidade.getIdcidade());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cidade> getAllCidades() {
        List<Cidade> cidades = new ArrayList<>();
        String sql = "SELECT * FROM cidade WHERE disponibilidade = true ORDER BY nome";
        try (Connection conexao = Db.getConexao();
             Statement statement = conexao.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado_idestado(rs.getString("estado_idestado"));
                cidades.add(cidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidades;
    }

    public Cidade getCidadeByCodigo(int codigoCidade) {
        Cidade cidade = null;
        String sql = "SELECT * FROM cidade WHERE idcidade=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoCidade);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    cidade = new Cidade();
                    cidade.setIdcidade(rs.getInt("idcidade"));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setEstado_idestado(rs.getString("estado_idestado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidade;
    }
}
