/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author HoTuan
 */
public class TypeProduct_ett {
    private Connection conn;
    private final String table="typeproduct";
    public TypeProduct_ett() throws ClassNotFoundException, SQLException{
        ConnectDB connectDB=new ConnectDB();
        conn=connectDB.connect();
    }
    public List<TypeProduct> getDataAll() throws SQLException {
        List<TypeProduct> lst= new ArrayList<TypeProduct>();
        ResultSet rs=null;
        String sqlCommand="select * from " + table ;
        Statement st;
            st = conn.createStatement();
            rs=st.executeQuery(sqlCommand);
            TypeProduct p;
            while(rs.next()){
               // if(rs.getString(11)==null){
                    p=new TypeProduct();
                    p.setType(Integer.parseInt(rs.getString(1)));
                    p.setName(rs.getString(2));
                    p.setNote(rs.getString(3));
                    if(rs.getString(4)!=null) p.setCreate(Integer.parseInt(rs.getString(4)));
                    if(rs.getString(5)!=null) p.setUpdate(Integer.parseInt(rs.getString(5)));
                    if(rs.getString(6)!=null)p.setDel(Integer.parseInt(rs.getString(6)));
                    lst.add(p);
                //}    
             }
           
        return lst;
    }
//     public static void main(String[] args) throws ClassNotFoundException, SQLException{
//        TypeProduct_ett p=new TypeProduct_ett();
//        
//       System.out.println(p.getDataAll().size());
//    }
}
