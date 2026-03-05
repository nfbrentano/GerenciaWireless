package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuarios;
import util.Db;

public class UsuarioDAO {
    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = Db.getConexao();
    }

    public Usuarios getSingle(String login) {
        Usuarios user = null;
        String sql = "SELECT idusuarios, nome, senha, supervisor FROM usuarios WHERE nome = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user = new Usuarios();
                    user.setIdusuarios(rs.getInt("idusuarios"));
                    user.setNome(rs.getString("nome"));
                    user.setSenha(rs.getString("senha"));
                    user.setSupervisor(rs.getString("supervisor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
