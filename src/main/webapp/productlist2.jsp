<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物商城</title>
<link href="css/category.css" rel="stylesheet" type="text/css" />

</head>
		
<body>
<!--wrap-->
<div id="wrap">
	<%@ include file="top.jsp" %>
    <!--gnb-->
    <div id="gnb">
    	<div class="ttl">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
       		<%@ include file="menu.jsp" %>
    </div>
    <!--//gnb-->
   <form action="${url }" method="post">
    <div id="path">
    	商品分类：
       <select name="fenleiid">
      
      <option value="">所有分类</option>
      
      <c:forEach items="${fenleilist}"  var="fenlei">
      <option value="${fenlei.id }" <c:if test="${fenleiid==fenlei.id }">selected</c:if> >${fenlei.fname }</option>
      </c:forEach>
      </select>
      
      是否推荐：
       <select name="tuijian">
      
      <option value="">所有选项</option>
      
     <option value="已推荐" <c:if test="${tuijian=='已推荐' }">selected</c:if> >已推荐</option>
     <option value="未推荐" <c:if test="${tuijian=='未推荐' }">selected</c:if> >未推荐</option>
      </select>
      
       商品名：<input type="text"  name="pname" value="${pname }" /> 
       
       价格区间：<input type="text"  name=jiage1 value="${jiage1 }" />--<input type="text"  name="jiage2" value="${jiage2 }" /> 
       
       <input type="submit"  value="查询" />    
    </div>
    </form>
    <!--//path-->
    <!--product_gallery-->
    <div id="prodgal">
     
    
    <c:forEach items="${list}" var="product">
    	<dl>
        	<dt><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }" width="100%" height="100%" alt="书架" /></a></dt>
            <dd><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></dd>
            <dd><span><a href="productmethod!productshow.action?id=${product.id }">${product.jiage }</a></span></dd>
        </dl>
     </c:forEach>
     
       
    	
       
    
    	
                  	
    </div>
    
    <div align="center" style="font-size: 20px;font-weight: bold;">${pagerinfo }</div>
    
    <br/>
    
    <!--//product_gallery-->
    <!--bottom-->
    <%@ include file="down.jsp" %>
    <!--//bottom-->
</div>
<!--//wrap-->
</body> 
</html>


