package dao;


import java.sql.Connection;


public final class FabricaDeConexiones {
    private static final String URL = "jdbc:mysql://localhost:3306/supermercado";
    private static final String USER = "root";
    private static final String PASS = "curso";
    
    private FabricaDeConexiones() {}


    public static Connection getConnection() throws java.sql.SQLException {
        return java.sql.DriverManager.getConnection(URL, USER, PASS);
    }
}
