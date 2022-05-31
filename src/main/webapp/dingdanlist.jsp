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
订单号：<input name="orderid" type="text"  value="${orderid }"  style="border:2px solid black;" >

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
生成时间
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
总价
</span>
</td>



<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
支付方式
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
订单状态
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
${bean.orderid }&nbsp;
</span>


</td>






<td  height="20px" align="center" >

<span style="font-size: 14px;font-weight: bold;">
${bean.createtime }&nbsp;
</span>

</td>



<td  height="20px" align="center" >

<span style="font-size: 14px;font-weight: bold;">
￥${bean.zongjia }&nbsp;
</span>

</td>


<td  height="20px" align="center" >

<span style="font-size: 14px;font-weight: bold;">
${bean.pay }&nbsp;
</span>

</td>


<td  height="20px" align="center" >

<span style="font-size: 14px;font-weight: bold;">
${bean.status }&nbsp;
</span>

</td>



<td  height="20px" align="center" >
<a href="dingdanmethod!dingdanupdate3.action?id=${bean.id }">
<span style="font-size: 18px;font-weight: bold;">
订单详情
</span>
</a>
&nbsp;&nbsp;
<c:if test="${bean.status=='处理中'}">
<a href="dingdanmethod!dingdandelete.action?id=${bean.id }">
<span style="font-size: 18px;font-weight: bold;">
取消订单
</span>
</a>
</c:if>


<c:if test="${bean.status=='已发货'}">
<a href="dingdanmethod!dingdandelete3.action?id=${bean.id }">
<span style="font-size: 18px;font-weight: bold;">
确认收货
</span>
</a>
</c:if>

<c:if test="${bean.status=='已收货'}">
<a href="pingjiamethod!pingjialist5.action?did=${bean.id }">
<span style="font-size: 18px;font-weight: bold;">
商品评价
</span>
</a>
</c:if>

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


