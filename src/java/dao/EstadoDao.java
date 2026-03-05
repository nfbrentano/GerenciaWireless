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

public class EstadoDao {

    public EstadoDao() {
    }

    public void insertEstado(Estado estado) {
        String sql = "INSERT INTO estado(nome, pais_idpais, disponibilidade) VALUES (?, ?, true)";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, estado.getNome());
            preparedStatement.setString(2, estado.getPais_idpais());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEstado(int codigoEstado) {
        String sql = "UPDATE estado SET disponibilidade=false WHERE idestado=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoEstado);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEstado(Estado estado) {
        String sql = "UPDATE estado SET nome=?, pais_idpais=? WHERE idestado=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, estado.getNome());
            preparedStatement.setString(2, estado.getPais_idpais());
            preparedStatement.setInt(3, estado.getIdestado());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT * FROM estado WHERE disponibilidade = true ORDER BY nome";
        try (Connection conexao = Db.getConexao();
             Statement statement = conexao.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setPais_idpais(rs.getString("pais_idpais"));
                estados.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }

    public Estado getEstadoByCodigo(int codigoEstado) {
        Estado estado = null;
        String sql = "SELECT * FROM estado WHERE idestado=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoEstado);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    estado = new Estado();
                    estado.setIdestado(rs.getInt("idestado"));
                    estado.setNome(rs.getString("nome"));
                    estado.setPais_idpais(rs.getString("pais_idpais"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }
}
