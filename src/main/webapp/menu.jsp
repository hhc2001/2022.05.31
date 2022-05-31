<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
                <li ><a href="."><span>网站首页</span></a></li>
                
                <li ><a href="gonggaomethod!gonggaolist2.action"><span>商城公告</span></a></li>
                <li ><a href="productmethod!productlist2.action"><span>商品列表</span></a></li>
                
                
                <c:if test="${user==null}">
                <li ><a href="usermethod!register.action"><span>新用户注册</span></a></li>
                <li ><a href="usermethod!login3.action"><span>用户登录</span></a></li>
                </c:if>
                
                <c:if test="${user!=null}">
                 <li><a href="gouwuchemethod!gouwuchelist.action">我的购物车</a></li>
                <li><a href="dingdanmethod!dingdanlist.action">我的订单</a></li>
                <li><a href="pingjiamethod!pingjialist.action">我的评价</a></li>
                <li><a href="usermethod!userupdate.action">我的信息</a></li>
               
                
                </c:if>
                
            </ul>

