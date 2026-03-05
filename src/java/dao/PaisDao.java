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

public class PaisDao {

    public PaisDao() {
    }

    public void insertPais(Pais pais) {
        String sql = "INSERT INTO pais(nome, disponibilidade) VALUES (?, true)";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, pais.getNome());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePais(int codigoPais) {
        String sql = "UPDATE pais SET disponibilidade=false WHERE idpais=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoPais);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePais(Pais pais) {
        String sql = "UPDATE pais SET nome=? WHERE idpais=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, pais.getNome());
            preparedStatement.setInt(2, pais.getIdpais());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pais> getAllPaises() {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT * FROM pais WHERE disponibilidade = true ORDER BY nome";
        try (Connection conexao = Db.getConexao();
             Statement statement = conexao.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Pais pais = new Pais();
                pais.setIdpais(rs.getInt("idpais"));
                pais.setNome(rs.getString("nome"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }

    public Pais getPaisByCodigo(int codigoPais) {
        Pais pais = null;
        String sql = "SELECT * FROM pais WHERE idpais=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoPais);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pais = new Pais();
                    pais.setIdpais(rs.getInt("idpais"));
                    pais.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }
}
