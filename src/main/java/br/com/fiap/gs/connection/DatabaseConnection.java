package br.com.fiap.gs.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl"; // Conexão com o banco de dados plugável
        String user = "rm553383"; // Seu usuário
        String password = "281003"; // Sua senha
        return DriverManager.getConnection(url, user, password);
    }
}
