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

public class BairroDao {

    public BairroDao() {
    }

    public void insertBairro(Bairro bairro) {
        String sql = "INSERT INTO bairro(nome, cidade_idcidade, disponibilidade) VALUES (?, ?, true)";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, bairro.getNome());
            preparedStatement.setString(2, bairro.getCidade_idcidade());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBairro(int codigoBairro) {
        String sql = "UPDATE bairro SET disponibilidade=false WHERE idbairro=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoBairro);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBairro(Bairro bairro) {
        String sql = "UPDATE bairro SET nome=?, cidade_idcidade=? WHERE idbairro=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, bairro.getNome());
            preparedStatement.setString(2, bairro.getCidade_idcidade());
            preparedStatement.setInt(3, bairro.getIdbairro());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bairro> getAllBairros() {
        List<Bairro> bairros = new ArrayList<>();
        String sql = "SELECT * FROM bairro WHERE disponibilidade = true ORDER BY nome";
        try (Connection conexao = Db.getConexao();
             Statement statement = conexao.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Bairro bairro = new Bairro();
                bairro.setIdbairro(rs.getInt("idbairro"));
                bairro.setNome(rs.getString("nome"));
                bairro.setCidade_idcidade(rs.getString("cidade_idcidade"));
                bairros.add(bairro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bairros;
    }

    public Bairro getBairroByCodigo(int codigoBairro) {
        Bairro bairro = null;
        String sql = "SELECT * FROM bairro WHERE idbairro=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoBairro);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    bairro = new Bairro();
                    bairro.setIdbairro(rs.getInt("idbairro"));
                    bairro.setNome(rs.getString("nome"));
                    bairro.setCidade_idcidade(rs.getString("cidade_idcidade"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bairro;
    }
}
