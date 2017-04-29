/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Feichin
 */
public class DBConnectionFactoryImp extends DBConnectionFactory {
    
    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            /*Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup(dataSource);
            conn = ds.getConnection();*/ 
            return conn;
         } catch (SQLException ex) {
            if (conn == null){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url, username, password);
                    return conn;
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectionFactoryImp.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (ClassNotFoundException ex1) {
                    Logger.getLogger(DBConnectionFactoryImp.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(DBConnectionFactoryImp.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (NamingException ex) {
            //Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectionFactoryImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
