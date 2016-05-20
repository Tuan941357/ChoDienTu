/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HoTuan
 */
public class ConnectDB {
    private final String className="com.mysql.jdbc.Driver";
    private final String url="jdbc:mysql://localhost:3306/lamthu";
    private final String user="root";
    private final String pass="";
    private String table="product";
    
    private Connection connection;
    public Connection connect() throws ClassNotFoundException, SQLException{
       Class.forName(className);
       connection=DriverManager.getConnection(url, user, pass);
       return connection;
    }
    private void showData(ResultSet rs){
        try {
            while(rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException ex) {
            
        }
    }
    private ResultSet getData() throws SQLException{
        ResultSet rs=null;
        String sqlCommand="select * from " + table ;
        Statement st;
            st = connection.createStatement();
            rs=st.executeQuery(sqlCommand);
        return rs;
    }
//    public static void main(String[] args) throws ClassNotFoundException, SQLException{
//        ConnectDB conn= new ConnectDB();
//        conn.connect();
//       conn.showData(conn.getData());
//    }
}
