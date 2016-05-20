/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HoTuan
 */
public class TypeProduct {
    private int type;
    private String name;
    private String note;
    private int create;
    private int update;
    private int del;

    public TypeProduct() {
    }

    public TypeProduct(int type, String name, String note, int create, int update, int del) {
        this.type = type;
        this.name = name;
        this.note = note;
        this.create = create;
        this.update = update;
        this.del = del;
    }

   

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
