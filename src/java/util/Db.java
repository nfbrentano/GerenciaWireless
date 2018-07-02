/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Natanael
 */
public class Db {

    // Atributo estático para armazenar a conexão, que será utilizada
    // para todos os acessos ao banco de dados
    private static Connection conexaoDB = null;

    // Método estático para pegar a conexão
    public static Connection getConexao() {

        // Caso já exista uma conexão, retorna a existente
        if (conexaoDB != null) {

            return conexaoDB;

        } else { // Se não existir conexão, cria uma nova conexão
            try {

                // É interessante armazenar as informações de conexão num arquivo externo pois, dessa forma,
                // não será necessário recompilar o projeto caso mude o nome da base, usuário ou senha....
                Properties prop = new Properties();

                // Ler as propriedades do arquivo com dados de acesso
                InputStream is = Db.class.getClassLoader().getResourceAsStream("/base.properties");
                prop.load(is);

                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");

                Class.forName(driver);
                conexaoDB = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }

            return conexaoDB;
        }
    }
}
