<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
    	<h1><a href=".">
    	<span style="font-weight: bold;font-size: 40px;">购物商城</span>
    	</a></h1>
        <div id="welcome">您好，欢迎<font size="3" color="red">${user.username }</font>来到<font color="red">商城</font>！
        <c:if test="${user==null}">
        <a href = "usermethod!login3.action">[登录]</a><a href = "usermethod!register.action">[免费注册]</a>
        </c:if>
        
        <c:if test="${user!=null}">
        <a href = "usermethod!loginout2.action">[安全退出]</a>
        </c:if>
        
        </div>
      
       <form action="productmethod!productlist2.action" method="post" >
        <div id="search">
        	<input type="text" name="pname" value = "" class="t" />
            <input type="submit" value="搜索" class="b" />
            <div id="circle"></div>
        </div>
        </form>
        <div id="cab"><a href="gouwuchemethod!gouwuchelist.action"><img src="images/cab3.png" alt="购物车" /></a></div>
    </div>

