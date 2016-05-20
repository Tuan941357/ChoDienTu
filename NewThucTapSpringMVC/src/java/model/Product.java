/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.mail.Multipart;

/**
 *
 * @author HoTuan
 */
public class Product {
    private int ma;
    private String name;
    private int type;
    private int price;
    private int quantity;
    private String urlImage;
    private int size;
    private String description;
    private int create;
    private int update;
    private int del;
   

    public Product() {
    }

    public Product(int ma, String name, int type, int price, int quantity, String urlImage, int size, String description, int create, int update, int del) {
        this.ma = ma;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.urlImage = urlImage;
        this.size = size;
        this.description = description;
        this.create = create;
        this.update = update;
        this.del = del;
       
    }

   

   
    
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreate() {
        return create;
    }

    public void setCreate(int create) {
        this.create = create;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

   

}
