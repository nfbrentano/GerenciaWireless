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

    private static Properties prop = new Properties();

    static {
        try (InputStream is = Db.class.getClassLoader().getResourceAsStream("/base.properties")) {
            if (is != null) {
                prop.load(is);
                Class.forName(prop.getProperty("driver"));
            } else {
                System.err.println("Arquivo base.properties não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexao() throws SQLException {
        String url = System.getenv("DB_URL");
        if (url == null || url.trim().isEmpty()) {
            url = prop.getProperty("url");
        }

        String user = System.getenv("DB_USER");
        if (user == null || user.trim().isEmpty()) {
            user = prop.getProperty("user");
        }

        String password = System.getenv("DB_PASSWORD");
        if (password == null || password.trim().isEmpty()) {
            password = prop.getProperty("password");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
