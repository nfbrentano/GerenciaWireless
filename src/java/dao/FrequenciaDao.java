/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Frequencia;
import util.Db;

/**
 *
 * @author natan
 */
public class FrequenciaDao {

    private Connection conexao;

    public FrequenciaDao() {
        // Executa o método estático para realizar a conexão
        conexao = Db.getConexao();
    }

    public List<Frequencia> getAllFrequencias() {
        List<Frequencia> frequencias = new ArrayList<Frequencia>();

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM frequencia ORDER BY frequencia");
            while (rs.next()) {
                Frequencia frequencia = new Frequencia();
                frequencia.setIdfrequencia(rs.getInt("idfrequencia"));
                frequencia.setFrequencia(rs.getString("frequencia"));

                // Adicionar à lista
                frequencias.add(frequencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return frequencias;
    }

    public void insertFrequencia(Frequencia frequencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateFrequencia(Frequencia frequencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Frequencia getFrequenciaByCodigo(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteFrequencia(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
