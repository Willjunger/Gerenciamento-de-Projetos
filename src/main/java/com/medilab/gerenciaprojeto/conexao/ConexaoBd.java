/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medilab.gerenciaprojeto.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author medilab
 */
public class ConexaoBd {
    private final String url = "jdbc:firebirdsql:localhost:C:\\Users\\willi\\Desktop\\Ed\\TESTE.FDB";
    private final String user = "SYSDBA";
    private final String password = "masterkey";
    private Connection conSQL;
    //org.firebirdsql.jdbc.FBDriver

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conn = DriverManager.getConnection(url, user, password);
            this.conSQL = conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public Connection getConSQL() {
        return conSQL;
    }
}
