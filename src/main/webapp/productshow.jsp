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
<link href="css/single.css" rel="stylesheet" type="text/css" />

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
    <!--content-->
    <div id="content">
       
        <div id="right">
            <div id="board1">
            	<div id="ttl1"><div class="t1"></div>${bean.pname }</div>
                <div id="pit"><a href="#"><img src="uploadfile/${bean.imgpath }" width="100%" height="100%"  /></a></div>
                <div id="brief_itd1">
                
                	<div id="size" >商品名称：<span >${bean.pname }</span></div>
                
                	<div id="size" >价格：<span >￥${bean.jiage }</span></div>
                    
                    <div id="size" >点击数：<span >${bean.dj }</span></div>
                    
                    <div id="size" >销量：<span >${bean.xl }</span></div>
                    
                    <div id="size" >库存：<span >${bean.kucun }</span></div>
                    
                    <div id="size" >分类：<span >${bean.fenlei.fname }</span></div>
                     
                    
                    <div id="button">
                    	<div id="btn1">
                    	  <input type="button" class="btn1" value="立即购买"  onclick="javascript:window.location.href='gouwuchemethod!gouwucheadd.action?pid=${bean.id }';" />
                    	</div>
                        <div id="btn1">
                    	  <input type="button" class="btn2" value="加入购物车" onclick="javascript:window.location.href='gouwuchemethod!gouwucheadd2.action?pid=${bean.id }';" />
                    	</div>

                    </div>
	
                </div>
            </div>
         
            <!--content----right----board2-->
            <div id="board2">
            	<div id="ttl2">
                	<div class="t2">商品详情</div>
                </div>
                <div id="brief_itd2">
                	 <div style="width: 721px;word-wrap:break-word; word-break:break-all;">
                    	${bean.miaoshu }
                    </div>
                   
                </div>
            </div>
            <!--//content----right----board2-->
            <!--content----right----board3-->
         	<div id="board3">
           	<div id="ttl2">
           	  <div class="t2">评价详情</div>
                </div>
                
                <c:forEach items="${list}"  var="pj">
                <div class="b1">
                	<dl>
                    	<dt><a href="#"><img src="uploadfile/${pj.user.photo }" width="100%" height="100%" alt="admin" /></a></dt>
                        <dd><a href="#">${pj.user.truename }</a></dd>
                    </dl>
                    <div id="assess">
                    	<span>发表于${pj.shijian }</span>
                        <p>${pj.pneirong }</p>
                    </div>
                </div>
                </c:forEach>
                
                <div id="page">${pagerinfo }</div>
          </div>
            <!--//content----right----board3-->
        </div>
        <!--//content----right-->
    </div>
    <!--//content-->
    <!--ads-->
    <!--//ads-->
    <!--bottom-->
    <div id="bottom">
    </div>
    <!--//bottom-->
</div>
<!--//wrap-->
</body> 
</html>


