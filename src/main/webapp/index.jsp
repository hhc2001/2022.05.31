<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物商城</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />
	<!-- 引入JQuery支持的库 -->
  
		
</head>
<body>
<!--wrap-->
<div id="wrap">

	<%@ include file="top.jsp" %>

    <!--menu-->
   	<div id="menu">
    	<!--gnb-->
    	<div id="gnb">
        	<div class="ttl">所有分类</div>
        	
        	<%@ include file="menu.jsp" %>

        </div>
        <!--//gnb-->
        <!--snb-->
		<div id="snb">
        	<ul>
        	
        	<c:forEach items="${fenleilist}" var="fenlei">
        	<li><a href="productmethod!productlist2.action?fenleiid=${fenlei.id }">${fenlei.fname }</a></li>
        	</c:forEach>
        	
        		
            	</ul>
        </div>
        <!--//snb-->
        <!--center-->
        <div id="center">
        	<div id="gallery"><a href="#"><img src="uploadfile/${pic.imgpath }" width="100%" height="100%"/></a></div>
            
            
            <div id="recommend">精选推荐</div>
            <div id="thum">
               	<ul>
               	
               	<c:forEach items="${tuijianlist}" var="product">
                    <li id="li1"><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></li>
               	</c:forEach>     
                    
                  
                </ul>	
            </div>
        </div>
        <!--//center-->
        <!--right-->
        <div id="right">
        	<div id="ttl">
                <span>销售排行</span>
                    
            </div>
            <div id="rank">
            	<div id="introd1">&nbsp;</div>
                <div id="introd2">商品名</div>
                <div id="introd3">销量</a></div>
          	</div>
            
            <c:forEach items="${xllist}" var="product" varStatus="vv">
			<div id="rank">
            	<div id="introd1"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }" width="100%" height="100%" alt="图片" /></a></div>
                <div id="introd2"><a href="productmethod!productshow.action?id=${product.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${product.pname }</a></div>
                <div id="introd3"><a href="productmethod!productshow.action?id=${product.id }">${product.xl }</a></div>
          	</div>
          	</c:forEach>
          	
            
      	</div>
        <!--//right-->
    </div>
    <!--//menu-->
    <!--content-->
    <div id="content">
    <!---------------------------------board1--------------------------------->
   	  <div id="board">
      <!--content----lnb-->
      	<div id="lnb">
                <div id="clothing">${fenlei1 }</div>
                <div id="lnb1">
                </div>
        <a href="productmethod!productlist2.action?fenleiid=${fenlei1id }"><img src="images/more2.jpg" alt="更多" class="img2" /></a>        </div>
     
      
      
       <c:forEach items="${productlist1}" var="product" varStatus="vv">
       
       <c:if test="${vv.index!=0}">
       	<div id="class2">
           	<div id="pit2"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }" width="100%" height="100%" /></a></div>
            <div id="introduction"><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        
        <c:if test="${vv.index==0}">
        <div id="class1">
            <div id="pit1"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }"  width="100%" height="100%" /></a></div>
            <div id="introduction"><span><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></span></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        </c:forEach>
        
        
        
        
      </div>
 
     
      <div id="board">
      <!--content----lnb-->
      	<div id="lnb">
                <div id="clothing">${fenlei2 }</div>
                <div id="lnb1">
                </div>
        <a href="productmethod!productlist2.action?fenleiid=${fenlei2id }"><img src="images/more2.jpg" alt="更多" class="img2" /></a>        </div>
      
      <c:forEach items="${productlist2}" var="product" varStatus="vv">
       
       <c:if test="${vv.index!=0}">
       	<div id="class2">
           	<div id="pit2"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }" width="100%" height="100%" /></a></div>
            <div id="introduction"><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        
        <c:if test="${vv.index==0}">
        <div id="class1">
            <div id="pit1"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }"  width="100%" height="100%" /></a></div>
            <div id="introduction"><span><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></span></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        </c:forEach>
        
      </div>
  
  
  
      <div id="board">
      
      	<div id="lnb">
                <div id="clothing">${fenlei3 }</div>
                <div id="lnb1">
                </div>
        <a href="productmethod!productlist2.action?fenleiid=${fenlei3id }"><img src="images/more2.jpg" alt="更多" class="img2" /></a>        </div>
      
      <c:forEach items="${productlist3}" var="product" varStatus="vv">
       
       <c:if test="${vv.index!=0}">
       	<div id="class2">
           	<div id="pit2"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }" width="100%" height="100%" /></a></div>
            <div id="introduction"><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        
        <c:if test="${vv.index==0}">
        <div id="class1">
            <div id="pit1"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }"  width="100%" height="100%" /></a></div>
            <div id="introduction"><span><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></span></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        </c:forEach>
        
      </div>
      
      
      <div id="board">
      <!--content----lnb-->
      	<div id="lnb">
                <div id="clothing">${fenlei4 }</div>
                <div id="lnb1">
                </div>
        <a href="productmethod!productlist2.action?fenleiid=${fenlei4id }"><img src="images/more2.jpg" alt="更多" class="img2" /></a>        </div>
      
      <c:forEach items="${productlist4}" var="product" varStatus="vv">
       
       <c:if test="${vv.index!=0}">
       	<div id="class2">
           	<div id="pit2"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }" width="100%" height="100%" /></a></div>
            <div id="introduction"><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        
        <c:if test="${vv.index==0}">
        <div id="class1">
            <div id="pit1"><a href="productmethod!productshow.action?id=${product.id }"><img src="uploadfile/${product.imgpath }"  width="100%" height="100%" /></a></div>
            <div id="introduction"><span><a href="productmethod!productshow.action?id=${product.id }">${product.pname }</a></span></div>
            <div id="price">
            	<a href="productmethod!productshow.action?id=${product.id }">￥${product.jiage }</a> 
            </div>
        </div>
        </c:if>
        </c:forEach>
        
      </div>
      
    <!---------------------------------//board4--------------------------------->
    
    </div>
    <!--//content-->
    <!--bottom-->
    <%@ include file="down.jsp" %>
</div>
<!--//wrap-->

</body>
</html>


