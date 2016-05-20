/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import model.*;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author HoTuan
 */
@Controller

public class MainController {
    @RequestMapping(value="/QL_Product", method=RequestMethod.GET)
    public String product(@RequestParam(value="act", defaultValue="") String act,@RequestParam(value = "id", defaultValue = "0") int id, ModelMap model){
        Product obj_edit=new Product();
        switch(act){
            case "del":
                try {
                    product_ett obj = new product_ett();
                    if(obj.del(id,1)){
                        model.put("error", "Xóa thành công");
                    }else{
                        model.put("error", "Xóa thất bại");
                    }
                } catch (Exception ex) {
                    model.put("error",ex.getMessage());
                }
                break;
            case "edit":
                try {
                    product_ett obj = new product_ett();
                    Product p=new Product();
                    obj_edit=obj.getByID(id);
                } catch (Exception ex) {
                    model.put("error","Loi");
                }
                break;
            default:
                break;
        }
        // tạo các tham số cho view
        if(act.compareTo("edit")==0){
             model.put("action", "edit");
             model.put("btn","Cap nhat");
        }else{
             model.put("action", "add");
             model.put("btn","Them");
        }
        
        model.put("product", obj_edit);
        //tham so dữ liệu hiện thị các product
        List<Product> arrlist=new ArrayList<Product>();
        try {
            product_ett obj=new product_ett();
            arrlist=obj.getDataAll();
            model.put("arr", arrlist);
        } catch (Exception ex) {
           model.put("arr", arrlist);
        }
        
        
        //tham số cho select của loại hàng
        List<TypeProduct> arrshow= new ArrayList<TypeProduct>();
        try {
            TypeProduct_ett obj=new TypeProduct_ett();
            arrshow=obj.getDataAll();
            model.put("arrdropdown", arrshow);
        } catch (Exception ex) {
           model.put("arrdropdown", arrshow);
        }
        return "QL_Product";
    }

    @RequestMapping(value="/QL_Product", method=RequestMethod.POST)
    public String addProduct(ModelMap model, HttpServletRequest request){
        //request các tham so
        String act="";
        act=request.getParameter("act");
        String id =request.getParameter("ma");
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        String price=request.getParameter("price");
        String quantity=request.getParameter("quantity");
        String urlImage=request.getParameter("urlImage");
        if(urlImage.compareTo("")==0){
             urlImage=request.getParameter("urlImageOld");          
        }else{
            String tg="http://localhost:8080/NewThucTapSpringMVC/upload/";
            urlImage=tg+urlImage;
        } 
        String size =request.getParameter("size");
        String description=request.getParameter("description");
        
        Product myProduct = new Product();
        if(id!=null && id.compareTo("")!=0)myProduct.setMa(Integer.parseInt(id));
        myProduct.setName(name);
        if(type!=null && type.compareTo("")!=0)myProduct.setType(Integer.parseInt(type));
        if(price!=null && price.compareTo("")!=0)myProduct.setPrice(Integer.parseInt(price));
        if(quantity!=null && quantity.compareTo("")!=0)myProduct.setQuantity(Integer.parseInt(quantity));
        myProduct.setUrlImage(urlImage);
        if(size!=null && size.compareTo("")!=0)myProduct.setSize(Integer.parseInt(size));
        myProduct.setDescription(description);
        
        //kiểm tra lỗi các đầu vào
        CheckError err=new CheckError();
        boolean check=true;
        if(name.compareTo("")==0){
           err.setName("Tên không được để trống!");
           check=false;
        }
        
        if(price.compareTo("")!=0){
            if(Integer.parseInt(price)<=0){
                err.setPrice("Giá không được nhỏ hơn hoặc bằng 0");
                check=false;
            }
        }else{
            err.setPrice("Giá không được để trống");
            check=false;
        }
        
        if(quantity.compareTo("")!=0){
            if(Integer.parseInt(quantity)<=0){
                err.setQuantity("Số lượng không được nhỏ hơn hoặc bằng 0");
                check=false;
            }
        }else{
            err.setQuantity("Số lượng không được trống");
            check=false;
        }
        if(size.compareTo("")!=0){
            if(Integer.parseInt(size)<=0){
                err.setSize("Kich cỡ không được nhỏ hơn hoặc bằng 0");
                check=false;
            }
        }else{
            err.setSize("Kich cỡ không được để trống");
            check=false;
        }
        
        
        if(check==false){
            switch(act){
            case "edit":
                model.put("btn","Cập nhật");
                model.put("action","edit");
                
                break;
            default:
                model.put("btn","Them");
                model.put("action","add");
                break;
            }
            model.put("err", err);
            if(id.compareTo("")==0 || id==null || id.compareTo("0")==0) myProduct.setMa(1);
            model.put("product", myProduct);
            List<Product> arrlist=new ArrayList<Product>();
            try {
                product_ett obj=new product_ett();
                arrlist=obj.getDataAll();
                model.put("arr", arrlist);
            } catch (Exception ex) {
                model.put("arr", arrlist);
            }
            List<TypeProduct> arrshow= new ArrayList<TypeProduct>();
            try {
                TypeProduct_ett obj=new TypeProduct_ett();
                arrshow=obj.getDataAll();
                model.put("arrdropdown", arrshow);
            } catch (Exception ex) {
                model.put("arrdropdown", arrshow);
            }
            return "QL_Product";
        }
        
        //thực hiện thao tác với csdl khi check lỗi hoàn thành
        switch(act){
            case "add":
                try {
                   
                    myProduct.setUrlImage(urlImage);
                    product_ett obj=new product_ett();
                    myProduct.setCreate(1);
                    if(obj.insert(myProduct)){
                        model.put("error", "Thêm thành công");
                    }else{
                        model.put("error","Thêm thất bại");
                    }
                } catch (Exception ex) {
                    model.put("error","Loi thuc thi Them");
                    model.put("btn","Them");
                    model.put("action","add");
                    myProduct.setType(1);
                    model.put("product", myProduct);
                    List<Product> arrlist=new ArrayList<Product>();
                    try {
                        product_ett obj=new product_ett();
                        arrlist=obj.getDataAll();
                        model.put("arr", arrlist);
                    } catch (Exception ex1) {
                        model.put("arr", arrlist);
                    }
                    List<TypeProduct> arrshow= new ArrayList<TypeProduct>();
                    try {
                        TypeProduct_ett obj=new TypeProduct_ett();
                        arrshow=obj.getDataAll();
                        model.put("arrdropdown", arrshow);
                    } catch (Exception ex1) {
                        model.put("arrdropdown", arrshow);
                    }
                    return "QL_Product";
                }
                break;
            case "edit":
                try {
                    myProduct.setUrlImage(urlImage);
                    product_ett obj=new product_ett();
                    myProduct.setUpdate(1);
                    if(obj.update(myProduct)){
                        model.put("error", "Sửa thành công");
                    }else{
                        model.put("error","Sửa Thất bại");
                    }
                } catch (Exception ex) {
                     model.put("error",ex.getMessage());
                }
                break;
            default:
                break;
        }
        
        //tham so load lại dữ liệu
        model.put("btn","Them");
        model.put("action","add");
        model.put("product", new Product());
        List<Product> arrlist=new ArrayList<Product>();
        try {
            product_ett obj=new product_ett();
            arrlist=obj.getDataAll();
            model.put("arr", arrlist);
        } catch (Exception ex) {
           model.put("arr", arrlist);
        }
        List<TypeProduct> arrshow= new ArrayList<TypeProduct>();
                    try {
                        TypeProduct_ett obj=new TypeProduct_ett();
                        arrshow=obj.getDataAll();
                        model.put("arrdropdown", arrshow);
                    } catch (Exception ex1) {
                        model.put("arrdropdown", arrshow);
                    }
        return "QL_Product";
    }
}
