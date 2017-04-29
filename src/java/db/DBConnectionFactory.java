/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;

/**
 *
 * @author Feichin
 */
public abstract class DBConnectionFactory {
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/bigas";
    String username = "root";
    String password = "1234";
    String dataSource = "jdbc:mysql://127.0.0.1:3306/bigas";

    public static DBConnectionFactory getInstance() {
        return new DBConnectionFactoryImp();
    }

    public abstract Connection getConnection();
}
