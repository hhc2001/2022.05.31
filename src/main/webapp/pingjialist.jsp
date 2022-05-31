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
   
    
    <div id="prodgal">
    <form action="${url }" method="post">

商品名：<input name="pname" type="text"  value="${pname }"  style="border:2px solid black;" >
<input type="submit"  value="查询"/>
</form>

<table width="100%" border="1" >


<tr bgcolor="#FF9900">
<td align="center" colspan="20" height="35px" >

<span style="font-size: 25px;font-weight: bold;">
<a href=".">首页</a>>>${title }
</span>

</td>
</tr>



<tr  bgcolor="#eff4fe">


<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
订单号
</span>
</td>


<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
商品名
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
是否评价
</span>
</td>



<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
评价内容
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
评价时间
</span>
</td>



<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
操作
</span>
</td>



</tr>

<c:forEach items="${list}" var="bean">

<tr  bgcolor="#eff4fe">


<td  height="20px" align="center" >
<span style="font-size: 14px;font-weight: bold;">
${bean.dingdan.orderid } &nbsp;
</span>
</td>



<td  height="20px" align="center" >
<span style="font-size: 14px;font-weight: bold;">
${bean.product.pname } &nbsp;
</span>
</td>


<td  height="20px" align="center" >
<span style="font-size: 14px;font-weight: bold;">
<c:if test="${bean.status==0}">未评价</c:if>
<c:if test="${bean.status==1}">已评价</c:if>
</span>
</td>


<td  height="20px" align="center" >
<span style="font-size: 14px;font-weight: bold;">
${bean.pneirong }&nbsp;
</span>
</td>



<td  height="20px" align="center" >
<span style="font-size: 14px;font-weight: bold;">
${bean.shijian }&nbsp;
</span>
</td>





<td  height="20px" align="center" >
<c:if test="${bean.status==1}">
<a href="pingjiamethod!pingjiadelete.action?id=${bean.id }">
<span style="font-size: 18px;font-weight: bold;">
删除
</span>
</a>&nbsp;&nbsp;&nbsp;
</c:if>
<a href="pingjiamethod!pingjiaadd.action?id=${bean.id }">
<span style="font-size: 18px;font-weight: bold;">
评价
</span>
</a>

</td>


</tr>

</c:forEach>



<tr>
<td  align="center" colspan="6">
${pagerinfo }

</td>




</table>


                  	
    </div>
    
   
    
    
    <!--//product_gallery-->
    <!--bottom-->
    <%@ include file="down.jsp" %>
    <!--//bottom-->
</div>
<!--//wrap-->
</body> 
</html>


