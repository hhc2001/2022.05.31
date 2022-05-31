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
公告标题：<input name="gtitle" type="text"  value="${gtitle }"  style="border:2px solid black;" >

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
公告标题
</span>
</td>



<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
添加时间
</span>
</td>





</tr>

<c:forEach items="${list}" var="bean">

<tr  bgcolor="#eff4fe">

<td  height="20px" align="center" >

<a href="gonggaomethod!gonggaoshow.action?id=${bean.id }">
<span style="font-size: 14px;font-weight: bold;">
${bean.gtitle }&nbsp;
</span>
</a>

</td>






<td  height="20px" align="center" >

<span style="font-size: 14px;font-weight: bold;">
${bean.ctime }&nbsp;
</span>

</td>






</tr>




</c:forEach>



<tr>
<td  align="center" colspan="6">
${pagerinfo }

</td>




</table>


                  	
    </div>
    
   
    
    <br/>
    
    <!--//product_gallery-->
    <!--bottom-->
    <%@ include file="down.jsp" %>
    <!--//bottom-->
</div>
<!--//wrap-->
</body> 
</html>


