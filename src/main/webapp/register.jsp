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
    <script type="text/javascript">





function checkform(){




	 if (document.getElementById('usernameid').value=="")
	{
		alert("用户名不能为空");
		return false;
	}
	var valid=/^\w+$/;
	if(!valid.test(document.getElementById('usernameid').value)){
		alert("用户名必须是数字、字母或下划线");
		return false;
	}

	if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	if (document.getElementById('passwordid').value.length<6)
	{
		alert("密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('passwordid').value)
	{
		alert("确认密码与密码不一致");
		return false;
	}
		 
	if (document.getElementById('truenameid').value=="")
	{
		alert("姓名不能为空");
		return false;
	}
	

	
	if (document.getElementById('phoneid').value=="")
	{
		alert("手机不能为空");
		return false;
	}
	
	valid=/^0?1[3,4,5,6,7,8,9][0,1,2,3,4,5,6,7,8,9]\d{8}$/;  
	if(!valid.test(document.getElementById('phoneid').value)){
		alert("请输入正确的手机格式");
		return false;
	}
	
	
	
	
	return true;
}
</script> 
    
    <form method="post" action="${url }"  onsubmit="return checkform()">		

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
用户名：
</span>
</td>
<td ><input name="username" type="text" id="usernameid"    style="width: 500px;height: 25px;"/></td>
</tr>


<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
密码：
</span>
</td>
<td ><input name="password" type="password" id="passwordid"    style="width: 500px;height: 25px;"/></td>
</tr>


<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
确认密码：
</span>
</td>
<td ><input name="password2" type="password" id="password2id"    style="width: 500px;height: 25px;"/></td>
</tr>


<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
姓名：
</span>
</td>
<td ><input name="truename" type="text" id="truenameid"    style="width: 500px;height: 25px;"/></td>
</tr>



<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
手机：
</span>
</td>
<td ><input name="phone" type="text" id="phoneid"    style="width: 500px;height: 25px;"/></td>
</tr>




<tr  bgcolor="#eff4fe">
<td  height="30px" align="right" width="30%">
<span style="font-size: 18px;font-weight: bold;">
操作：
</span>
</td>
<td  >
 <input type="submit" value="注&nbsp;&nbsp;&nbsp;&nbsp;册" style="width: 100px;height: 25px;" /> 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="重&nbsp;&nbsp;&nbsp;&nbsp;置" style="width: 100px;height: 25px;"/>
   

</td>
</tr>


</table>
</form>
                  	
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


