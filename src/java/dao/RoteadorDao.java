package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Roteador;
import util.Db;

public class RoteadorDao {

    public RoteadorDao() {
    }

    public void insertRoteador(Roteador roteador) {
        String sql = "INSERT INTO pontoacesso(ssid, modelo, largurabanda, frequencia, iproteador, usuario, pass, disponibilidade) VALUES (?, ?, ?, ?, ?, ?, ?, true)";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, roteador.getSsid());
            preparedStatement.setString(2, roteador.getModelo());
            preparedStatement.setString(3, roteador.getLargurabanda());
            preparedStatement.setString(4, roteador.getFrequencia());
            preparedStatement.setString(5, roteador.getIproteador());
            preparedStatement.setString(6, roteador.getUsuario());
            preparedStatement.setString(7, roteador.getPass());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoteador(int codigoRoteador) {
        String sql = "UPDATE pontoacesso SET disponibilidade=false WHERE idpontoacesso=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigoRoteador);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoteador(Roteador roteador) {
        String sql = "UPDATE pontoacesso SET ssid=?, modelo=?, largurabanda=?, frequencia=?, iproteador=?, usuario=?, pass=? WHERE idpontoacesso=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, roteador.getSsid());
            preparedStatement.setString(2, roteador.getModelo());
            preparedStatement.setString(3, roteador.getLargurabanda());
            preparedStatement.setString(4, roteador.getFrequencia());
            preparedStatement.setString(5, roteador.getIproteador());
            preparedStatement.setString(6, roteador.getUsuario());
            preparedStatement.setString(7, roteador.getPass());
            preparedStatement.setInt(8, roteador.getIdpontoacesso());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Roteador> getAllRoteadores() {
        List<Roteador> roteadores = new ArrayList<>();
        String sql = "SELECT * FROM pontoacesso WHERE disponibilidade = true ORDER BY ssid";
        try (Connection conexao = Db.getConexao();
             Statement statement = conexao.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Roteador roteador = new Roteador();
                roteador.setIdpontoacesso(rs.getInt("idpontoacesso"));
                roteador.setSsid(rs.getString("ssid"));
                roteador.setModelo(rs.getString("modelo"));
                roteador.setLargurabanda(rs.getString("largurabanda"));
                roteador.setFrequencia(rs.getString("frequencia"));
                roteador.setIproteador(rs.getString("iproteador"));
                roteador.setUsuario(rs.getString("usuario"));
                roteador.setPass(rs.getString("pass"));
                roteadores.add(roteador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roteadores;
    }

    public Roteador getRoteadorByCodigo(int codigoRoteador) {
        Roteador roteador = null;
        String sql = "SELECT * FROM pontoacesso WHERE idpontoacesso=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigoRoteador);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    roteador = new Roteador();
                    roteador.setIdpontoacesso(rs.getInt("idpontoacesso"));
                    roteador.setSsid(rs.getString("ssid"));
                    roteador.setModelo(rs.getString("modelo"));
                    roteador.setLargurabanda(rs.getString("largurabanda"));
                    roteador.setFrequencia(rs.getString("frequencia"));
                    roteador.setIproteador(rs.getString("iproteador"));
                    roteador.setUsuario(rs.getString("usuario"));
                    roteador.setPass(rs.getString("pass"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roteador;
    }

    public Roteador getRoteadorBySsid(String ssid) {
        Roteador roteador = null;
        String sql = "SELECT * FROM pontoacesso WHERE ssid=?";
        try (Connection conexao = Db.getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, ssid);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    roteador = new Roteador();
                    roteador.setIdpontoacesso(rs.getInt("idpontoacesso"));
                    roteador.setSsid(rs.getString("ssid"));
                    roteador.setModelo(rs.getString("modelo"));
                    roteador.setLargurabanda(rs.getString("largurabanda"));
                    roteador.setFrequencia(rs.getString("frequencia"));
                    roteador.setIproteador(rs.getString("iproteador"));
                    roteador.setUsuario(rs.getString("usuario"));
                    roteador.setPass(rs.getString("pass"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roteador;
    }
}
