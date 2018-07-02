/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import model.Frequencia;
import model.Roteador;
import util.Db;

/**
 *
 * @author natan
 */
public class RoteadorDao {
    // Atributo para armazenar a conexao com o banco de dados

    private Connection conexao;

    public RoteadorDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    public int IdRoteador() throws SQLException {
        String sql = "select max(idpontoacesso+1) from pontoacesso";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        return 0;
    }

    public ResultSet ipRoteador(int idpontoacesso) throws SQLException {
        String sql = "select iproteador from pontoacesso WHERE idpontoacesso =?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, idpontoacesso);
        ResultSet rs = preparedStatement.executeQuery();

        return (ResultSet) rs.getObject("iproteador");
    }

    public void insertRoteador(Roteador roteador) {
        try {
            String sql = "INSERT INTO pontoacesso( ssid, modelo, largurabanda, frequencia, iproteador, usuario, pass, disponibilidade ) VALUES ( ?, true )";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, roteador.getSsid());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoteador(int codigoRoteador) {
        try {
            String sql = "UPDATE pontoacesso SET disponibilidade=false WHERE idpontoacesso=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parâmetros iniciam com 1
            preparedStatement.setInt(1, codigoRoteador);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoteador(Roteador roteador) throws SQLException {
        try {
            String sql = "UPDATE pontoacesso SET ssid=?, modelo=?, largurabanda=?, frequencia=?, iproteador=?, usuario=?, pass=? WHERE idpontoacesso=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Parâmetros iniciam com 1
            preparedStatement.setString(1, roteador.getSsid());
            preparedStatement.setInt(2, roteador.getIdpontoacesso());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Roteador> getAllRoteadores() {
        List<Roteador> roteadores = new ArrayList<Roteador>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM pontoacesso WHERE disponibilidade = true ORDER BY ssid");
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

                // Adicionar à lista
                roteadores.add(roteador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roteadores;
    }

    public Roteador getRoteadorByCodigo(int codigoRoteador) {
        // Instanciar objeto que será retornado
        Roteador roteadores = new Roteador();

        try {
            String sql = "SELECT * FROM pontoacesso WHERE idpontoacesso=?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, codigoRoteador);
            ResultSet rs = preparedStatement.executeQuery();

            // Atribuir retorno do banco aos atributos do objeto roteador
            if (rs.next()) {
                roteadores.setIdpontoacesso(rs.getInt("idpontoacesso"));
                roteadores.setSsid(rs.getString("ssid"));
                roteadores.setModelo(rs.getString("modelo"));
                roteadores.setLargurabanda(rs.getString("largurabanda"));
                roteadores.setFrequencia(rs.getString("frequencia"));
                roteadores.setIproteador(rs.getString("iproteador"));
                roteadores.setUsuario(rs.getString("usuario"));
                roteadores.setPass(rs.getString("pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roteadores;
    }

   

}
