/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author HoTuan
 */
public class product_ett {
    private Connection conn;
    private final String table="product";
    public product_ett() throws ClassNotFoundException, SQLException {
        ConnectDB connectDB=new ConnectDB();
        conn=connectDB.connect();
        
    }
    
    public List<Product> getDataAll() throws SQLException {
        List<Product> lst= new ArrayList<Product>();
        ResultSet rs=null;
        String sqlCommand="select * from " + table ;
        Statement st;
            st = conn.createStatement();
            rs=st.executeQuery(sqlCommand);
            Product p;
            while(rs.next()){
                if(rs.getString(11)==null){
                    p=new Product();
                    p.setMa(Integer.parseInt(rs.getString(1)));
                    p.setName(rs.getString(2));
                    if(rs.getString(3)!=null) p.setType(Integer.parseInt(rs.getString(3)));
                    if(rs.getString(4)!=null) p.setPrice(Integer.parseInt(rs.getString(4)));
                    if(rs.getString(5)!=null)p.setQuantity(Integer.parseInt(rs.getString(5)));
                    p.setUrlImage(rs.getString(6));
                    if(rs.getString(7)!=null) p.setSize(Integer.parseInt(rs.getString(7)));
                    p.setDescription(rs.getString(8));
                    if(rs.getString(9)!=null) p.setCreate(Integer.parseInt(rs.getString(9)));
                    if(rs.getString(10)!=null) p.setUpdate(Integer.parseInt(rs.getString(10)));
                    if(rs.getString(11)!=null)p.setDel(Integer.parseInt(rs.getString(11)));
                    lst.add(p);
                }    
             }
        return lst;
    }
    public Product getByID(int id) throws SQLException{
        Product p=new Product();
        ResultSet rs=null;
        String sqlCommand="select * from "+table+" where ma = ?";
        PreparedStatement pst=null;
        pst = conn.prepareStatement(sqlCommand);
        pst.setInt(1, id);
        rs=pst.executeQuery();
       while(rs.next()){
                 p.setMa(Integer.parseInt(rs.getString(1)));
                 p.setName(rs.getString(2));
                 if(rs.getString(3)!=null) p.setType(Integer.parseInt(rs.getString(3)));
                 if(rs.getString(4)!=null) p.setPrice(Integer.parseInt(rs.getString(4)));
                 if(rs.getString(5)!=null)p.setQuantity(Integer.parseInt(rs.getString(5)));
                 p.setUrlImage(rs.getString(6));
                 if(rs.getString(7)!=null) p.setSize(Integer.parseInt(rs.getString(7)));
                 p.setDescription(rs.getString(8));
                 if(rs.getString(9)!=null) p.setCreate(Integer.parseInt(rs.getString(9)));
                 if(rs.getString(10)!=null) p.setUpdate(Integer.parseInt(rs.getString(10)));
                 if(rs.getString(11)!=null) p.setDel(Integer.parseInt(rs.getString(11)));
              
        }
        return p;
    }
    public Boolean insert(Product product) throws SQLException{
        String sqlCommand = "insert into " + table + "(name,type,price,quantity,ulrImage,size,description,create_persion) value(?,?,?,?,?,?,?,?)";
        PreparedStatement pst =null;
       
            pst=conn.prepareStatement(sqlCommand);
            pst.setString(1,product.getName());
            pst.setInt(2, product.getType());
            pst.setInt(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setString(5,product.getUrlImage());
            pst.setInt(6, product.getSize());
            pst.setString(7, product.getDescription());
            pst.setInt(8, product.getCreate());
            if(pst.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
    }
    public Boolean update(Product product) throws SQLException{
        String sqlCommand = "update " + table + " set name=?,type=?,price=?,quantity=?,ulrImage=?,size=?,description=?,update_persion=? where ma=?";
        PreparedStatement pst =null;
       
            pst=conn.prepareStatement(sqlCommand);
            pst.setString(1,product.getName());
            pst.setInt(2, product.getType());
            pst.setInt(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setString(5,product.getUrlImage());
            pst.setInt(6, product.getSize());
            pst.setString(7, product.getDescription());
            pst.setInt(8, product.getUpdate());
            pst.setInt(9, product.getMa());
            if(pst.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
    }
    public Boolean del(int id, int del_persion) throws SQLException{
        String sqlCommand = "update " + table + " set del_persion=? where ma=?";
        PreparedStatement pst =null;
       
            pst=conn.prepareStatement(sqlCommand);
            pst.setInt(1, del_persion);
            pst.setInt(2, id);
            
            if(pst.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        product_ett p=new product_ett();
        Product obj=new Product();
        obj.setMa(33);
        obj.setName("thu");
        p.update(obj);
       
    }
}
