<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<base href="<%=basePath %>"/>
<TITLE>购物商城系统</TITLE>
<LINK href="web.files/css.css" type=text/css 
rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.5880" name=GENERATOR>

<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	
	return true;
	
}


</script>

</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width=500 align=center border=0>
  <TBODY>
    <TR>
      
      <TD align=middle height=20>
     ${title }
      </TD>
    
    </TR>
  </TBODY>
</TABLE>
<br/>

<form action="${url }" method="post" onsubmit="return checkform()">
<TABLE cellSpacing=1 borderColorDark=#ffffff cellPadding=0 align=center bgColor=#cccccc borderColorLight=#999999 border=0>
  <TBODY>
    
    
    
    
    
    
   
    
    <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
       订单号：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
      ${bean.orderid }
      </TD>
     
    </TR>
    
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
        收货人姓名：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
     ${bean.sjname }
      </TD>
     
    </TR>
    
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
        收货人地址：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
      ${bean.address }
      </TD>
     
    </TR>
    
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
        收货人手机：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
     ${bean.phone }
      </TD>
     
    </TR>
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
        支付方式：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
     ${bean.pay }
      </TD>
     
    </TR>
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
       备注：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
      ${bean.beizhu }
      </TD>
     
    </TR>
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
        生成时间：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
     ${bean.createtime }
      </TD>
     
    </TR>
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
       总价：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
     ￥${bean.zongjia }
      </TD>
     
    </TR>
    
     <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
       订单详情：
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
     ${bean.xiangqing }
      </TD>
     
    </TR>
    
     
    
    
    <TR>
      <TD width=300 bgColor=#f6f6f6 height=20>
        <DIV align=center>&nbsp;
        操作
        </DIV>
      </TD>
      <TD width=300 bgColor=#f6f6f6 height=20>
   <c:if test="${bean.status=='处理中'}">
   <input  onclick="javascript:window.location.href='dingdanmethod!dingdandelete2.action?id=${bean.id }';" style="width: 60px" type="button" value="发货" />
   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
   </c:if>
				<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
                                    
      </TD>
     
    </TR>
    
  </TBODY>
</TABLE>
</form>

</BODY>
</HTML>

