<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý sản phẩm</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="/resources/js/jquery-2.2.3.min.js"></script>
        <script src="/resources/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="http://code.angularjs.org/1.2.6/angular.js"></script> 
        <style>
            .checkerror{
                color:red;
            }
        </style>
    </head>
    <body  ng-app="validationApp" ng-controller="mainController">    
        <br>
        <div style="width: 50%; margin-left: 25%; border: 1px solid #44aaff;">
            <div style="background-size: 100% 100%; background-color: #44aaff; padding: 1px;">
                    <h3>Thêm sản phẩm</h3>
            </div>
            <div style="padding:7px;">
            <br>
            <form role="form" name="userForm" action="/NewThucTapSpringMVC/QL_Product.htm?act=${action}" method="POST" novalidate>             
                <input type="hidden" name="ma" value="{{user.ma}}">
                <div class="form-group">
                    <label>Tên Sản Phẩm </label>
                    <input  class="form-control" type="text" name="name" ng-model="user.name" placeholder="Bạn cần phải nhập tên" >
                    <p class="checkerror">{{err.name}}</p>
                 </div>
                 <div class="form-group">
                    <label>Chọn loại hàng</label>
                    <select  class="form-control" name="type">
                        <c:forEach var="i" items="${arrdropdown}">
                            <c:choose>
                                <c:when test="${i.type==product.type}"><option value="${i.type}" selected>${i.name}</option></c:when>
                                <c:otherwise><option value="${i.type}">${i.name}</option></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                 </div>
                 <div class="form-group" >
                    <label>Giá</label>
                    <input  class="form-control"  type="number" name="price" ng-model="user.price" placeholder="Bạn cần phải nhập giá" >
                    <p class="checkerror">{{err.price}}</p>
                 </div> 
                 <div class="form-group">
                    <label>Số lượng</label>
                    <input  class="form-control"  type="number" name="quantity" ng-model="user.quantity"  placeholder="Bạn cần phải nhập số lượng">
                    <p class="checkerror">{{err.quantity}}</p>
                 </div>
                <div class="form-group">
                    <label>Ảnh</label>
                    <p>
                        <c:if test="${product.ma!=0}">
                            <input type="hidden" name="urlImageOld" value="${product.urlImage}"/>
                            <img src="${product.urlImage}"  style="width:160px; height: 100px;" />
                       </c:if>
                    </p>
                    <input type="file"  class="form-control"   name="urlImage" placeholder="chọn ảnh">
                    <p class="checkerror">{{err.urlImage}}</p>
                 </div>
                <div class="form-group">
                    <label>Kích thước</label>
                    <input  class="form-control"  type="number" name="size"  ng-model="user.size" placeholder="nhập kích cỡ">
                    <p class="checkerror">{{err.size}}</p>
                 </div>
                <div class="form-group">
                    <label>Mô tả</label>
                    <textarea class="form-control"  cols="100" rows="5" name="description" ng-model="user.description" placeholder="Điền mô tả"></textarea>
                    <p class="checkerror">{{err.description}}</p>
                 </div>
                 <input  class="btn btn-default"  type="submit" onclick="{{nutSubmit}}" value="${btn}" ng-disabled="userForm.$invalid">&nbsp;<input  class="btn btn-warning"   type="reset" value="reset" >
            </form>
            </div>
        </div>
           
         
        <br><br>
        <h2 align="center" ><p style="color:red;">${error}</p></h2>
        <br>
        <div style="width: 50%; margin-left: 25%; border: 1px solid #44aaff;">
            <div style="background-size: 100% 100%; background-color: #44aaff; padding: 1px">
                <h3>Danh sach sản phẩm</h3>
            </div>
            <div style="padding:7px;">
        <table align="center" class="table">
            <tr>
                <th>Ten</th>
                <th>Loai</th>
                <th>Gia ban</th>
                <th>So luong</th>
                <th>Anh</th>
                <th>Kich thuoc</th>
                <th>Mo ta</th>
                <th>Thao tac</th>
            </tr>
            <c:forEach var="sp" items="${arr}">
                <tr>
                    <td>
                        ${sp.name}
                    </td>
                    <td>
                       ${sp.type}
                    </td>
                    <td>
                       ${sp.price}
                    </td>
                    <td>
                       ${sp.quantity}
                    </td>
                    <td>
                       <img src="${sp.urlImage}" style="width:160px; height: 100px;" />
                    </td>
                    <td>
                       ${sp.size}
                    </td>
                    <td>
                       ${sp.description}
                    </td>
                    <td>
                        <a href="/NewThucTapSpringMVC/QL_Product.htm?act=edit&id=${sp.ma}" <button type="button" class="btn btn-success  custom-width">Edit</button></a>
                        <a href="/NewThucTapSpringMVC/QL_Product.htm?act=del&id=${sp.ma}"><button type="button"  class="btn btn-danger custom-width">Remove</button></a>
                    </td>
                </tr>
             </c:forEach>
        </table>
            </div>
        </div>
        <br>
        <script>
            var validationApp = angular.module('validationApp', []);
            // create angular controller
            validationApp.controller('mainController', function($scope) {
                // function to submit the form after all validation has occurred            
                $scope.user={
                    ma:"${product.ma}",
                    name:"${product.name}",
                    price:${product.price},
                    quantity:${product.quantity},
                    size:${product.size},
                    description:"${product.description}"
                };
                $scope.err={
                    name:"${err.name}",
                    price:"${err.price}",
                    quantity:"${err.quantity}",
                    urlImage:"${err.urlImage}",
                    size:"${err.size}",
                    description:"${err.description}"
                };
                $scope.nutSubmit="return false";
            });
        </script>
    </body>
</html>
