package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Endereco;
import util.Db;

public class EnderecoDao {

    public EnderecoDao() {
    }

    public void insertEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco(rua, bairro_idbairro, disponibilidade) VALUES (?, ?, true)";
        try (Connection conexao = Db.getConexao();
                PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getBairro_idbairro());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEndereco(int codigoEndereco) {
        String sql = "UPDATE endereco SET disponibilidade=false WHERE idendereco=?";
        try (Connection conexao = Db.getConexao();
                PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoEndereco);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEndereco(Endereco endereco) {
        String sql = "UPDATE endereco SET rua=?, bairro_idbairro=? WHERE idendereco=?";
        try (Connection conexao = Db.getConexao();
                PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getBairro_idbairro());
            preparedStatement.setInt(3, endereco.getIdendereco());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Endereco> getAllEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT e.idendereco, e.rua, e.bairro_idbairro, "
                + "b.nome AS bairro_nome, c.nome AS cidade_nome, es.nome AS estado_nome "
                + "FROM endereco e "
                + "LEFT JOIN bairro b ON b.idbairro = CAST(NULLIF(e.bairro_idbairro, '') AS INTEGER) "
                + "LEFT JOIN cidade c ON c.idcidade = CAST(NULLIF(b.cidade_idcidade, '') AS INTEGER) "
                + "LEFT JOIN estado es ON es.idestado = CAST(NULLIF(c.estado_idestado, '') AS INTEGER) "
                + "WHERE e.disponibilidade = true ORDER BY e.idendereco DESC";
        try (Connection conexao = Db.getConexao();
                Statement statement = conexao.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdendereco(rs.getInt("idendereco"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro_idbairro(rs.getString("bairro_idbairro"));
                endereco.setBairro(rs.getString("bairro_nome"));
                endereco.setCidade(rs.getString("cidade_nome"));
                endereco.setEstado(rs.getString("estado_nome"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    public Endereco getEnderecoByCodigo(int codigoEndereco) {
        Endereco endereco = null;
        String sql = "SELECT e.idendereco, e.rua, e.bairro_idbairro, "
                + "b.nome AS bairro_nome, c.nome AS cidade_nome, es.nome AS estado_nome "
                + "FROM endereco e "
                + "LEFT JOIN bairro b ON b.idbairro = CAST(NULLIF(e.bairro_idbairro, '') AS INTEGER) "
                + "LEFT JOIN cidade c ON c.idcidade = CAST(NULLIF(b.cidade_idcidade, '') AS INTEGER) "
                + "LEFT JOIN estado es ON es.idestado = CAST(NULLIF(c.estado_idestado, '') AS INTEGER) "
                + "WHERE e.idendereco=?";
        try (Connection conexao = Db.getConexao();
                PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoEndereco);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    endereco = new Endereco();
                    endereco.setIdendereco(rs.getInt("idendereco"));
                    endereco.setRua(rs.getString("rua"));
                    endereco.setBairro_idbairro(rs.getString("bairro_idbairro"));
                    endereco.setBairro(rs.getString("bairro_nome"));
                    endereco.setCidade(rs.getString("cidade_nome"));
                    endereco.setEstado(rs.getString("estado_nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }
}
