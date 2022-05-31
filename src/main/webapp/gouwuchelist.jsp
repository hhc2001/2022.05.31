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
    <script language="javascript" type="text/javascript">

function changenum(id){
		var num = document.getElementById(id+"_num").value;
		var reg1 =  /^\d+$/;
	if (num.match(reg1) == null)
	{
		alert("购买数量必须为正整数");
		return false;
	}
		if (num == 0 )
	{
		alert("购买数量必须大于0的正整数");
		return false;
	}
		var now = new Date(); 
		var t = now.getTime()+''; 
		window.location.href="gouwuchemethod!gouwucheupdate2.action?id="+id+"&sl="+num+"&t="+t;
		
		
	}

</script>	
    
    
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
商品名
</span>
</td>


<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
库存
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
单价
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
购买数量
</span>
</td>

<td  height="20px" align="center" >
<span style="font-size: 18px;font-weight: bold;">
小计
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

<a href="productmethod!productshow.action?id=${bean.product.id }">
<span style="font-size: 14px;font-weight: bold;">
${bean.product.pname }&nbsp;
</span>
</a>

</td>

<td  height="20px" align="center" >


<span style="font-size: 14px;font-weight: bold;">
${bean.product.kucun }&nbsp;
</span>


</td>



<td  height="20px" align="center" >
<span style="font-size: 14px;font-weight: bold;">
￥${bean.product.jiage } &nbsp;
</span>
</td>


<td  height="20px" align="center" >

<span style="font-size: 14px;font-weight: bold;">

<input type="text" name="sl"  value="${bean.shuliang }" id="${bean.id }_num"  size="5"/>&nbsp;
    <a href="javascript:;"  onclick="changenum(${bean.id })">变更</a> &nbsp;</td>
</span>

</td>

<td  height="20px" align="center">

<span style="font-size: 14px;font-weight: bold;">
￥${bean.product.jiage*bean.shuliang }&nbsp;
</span>

</td>


<td  height="20px" align="center">


<a href="gouwuchemethod!gouwuchedelete.action?id=${bean.id }" onclick=" return confirm('确定要取消吗?'); ">
<span style="font-size: 18px;font-weight: bold;">取消
</span>
</a>

</td>


</tr>




</c:forEach>



<tr  bgcolor="#eff4fe">
<td  height="20px" align="center" >

<span style="font-size: 25px;">总计：￥${zongjia }</span>

</td>

<td  height="20px" align="center" colspan="5" >
<a href="dingdanmethod!dingdanadd.action">
<img src="images/js.jpg"></img>
</a>


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


