
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bagat
 */
public abstract class Operations {

    static Connection conn = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public Operations() {
        //jdbc:mysql://localhost:3306/nortwind
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.dbName + "?useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection(url, Database.userName, Database.password);
            System.out.println("Connection Success");
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection Failure");
        }

    }

    abstract ArrayList getOperations();

}
