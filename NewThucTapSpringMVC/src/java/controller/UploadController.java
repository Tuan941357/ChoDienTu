/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author HoTuan
 */
@Controller
@RequestMapping(value="/upload")
public class UploadController {
    @RequestMapping(value="/process", method=RequestMethod.POST)
    public String save(HttpServletRequest request, ModelMap model){
        String path ;
       
        path=request.getRealPath("/upload");      
             path=path.substring(0,path.indexOf("\\build"));
            path=path+"\\web\\upload\\";
            DiskFileItemFactory d = new DiskFileItemFactory();
            ServletFileUpload uploader = new ServletFileUpload(d);
            try{
                List<FileItem> lst = uploader.parseRequest(request);
                for( FileItem fileItem : lst){
                    if(fileItem.isFormField()==false){
                        fileItem.write(new File(path+"/"+fileItem.getName()));
                    }
                }
                model.put("error", "Upload ảnh thành công");
            }catch(Exception ex){
                model.put("error", "Upload ảnh thất bại");
            }
            return "index";
    }
}
