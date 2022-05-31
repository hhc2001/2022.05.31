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
    <table width="100%" border="1" >

<tr bgcolor="#FF9900">
<td align="center" colspan="2" height="35px" >

<span style="font-size: 25px;font-weight: bold;">
<a href=".">首页</a>>>${title }
</span>


</td>
</tr>

<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
订单号：
</span>
</td>
<td >
${bean.orderid }
</td>
</tr>




<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
收货人姓名：
</span>
</td>
<td >
${bean.sjname }
</td>
</tr>

<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
收货人地址：
</span>
</td>
<td >
${bean.address }
</td>
</tr>

<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
收货人手机：
</span>
</td>
<td >
${bean.phone }
</td>
</tr>

<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
支付方式：
</span>
</td>
<td >
${bean.pay }
</td>
</tr>

<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
备注：
</span>
</td>
<td >
${bean.beizhu }
</td>
</tr>





<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
生成时间：
</span>
</td>
<td >
${bean.createtime }
</td>
</tr>



<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
总价：
</span>
</td>
<td >
￥${bean.zongjia }
</td>
</tr>

<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
订单详情：
</span>
</td>
<td >
${bean.xiangqing }
</td>
</tr>



<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
操作：
</span>
</td>
<td  >

    <input onclick="window.location.href='dingdanmethod!dingdanlist.action';" type="button" value="返&nbsp;&nbsp;&nbsp;&nbsp;回" style="width: 100px;height: 25px;"/>
   

</td>
</tr>


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


