package com.action;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.dao.PingjiaDao;
import com.model.Pingjia;
import com.model.User;
import com.util.Pager;
import com.util.Util;


public class PingjiaAction extends BaseAction {

	private static final long serialVersionUID = -4304509122548259589L;

	
	
	private PingjiaDao pingjiaDao;



	public PingjiaDao getPingjiaDao() {
		return pingjiaDao;
	}



	public void setPingjiaDao(PingjiaDao pingjiaDao) {
		this.pingjiaDao = pingjiaDao;
	}
	
	
	//评价列表
	public String pingjialist5() {
		HttpServletRequest request = this.getRequest();
		
		String did = request.getParameter("did");
		
		String pname = request.getParameter("pname");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

	
		
		if (pname != null && !"".equals(pname)) {

			sb.append(" product.pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("pname", pname);
		}
		
	

		sb.append("  dingdan.id="+did+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = pingjiaDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", pingjiaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "pingjiamethod!pingjialist5.action?did="+did+"", "共有" + total + "条记录"));
		request.setAttribute("url", "pingjiamethod!pingjialist5.action?did="+did);
		request.setAttribute("url2", "pingjiamethod!pingjia");
		request.setAttribute("title", "商品评价");
		this.setUrl("pingjialist5.jsp");
		return SUCCESS;

	}
	
	

	//跳转到添加评价页面
		public String pingjiaadd() {
			HttpServletRequest request = this.getRequest();
			String id = request.getParameter("id");
			
			request.setAttribute("url", "pingjiamethod!pingjiaadd2.action?id="+id);
			request.setAttribute("title", "商品评价");
			this.setUrl("pingjiaadd.jsp");
			return SUCCESS;
		}
		
		


		//评价操作
		public void pingjiaadd2() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter writer =  this.getPrintWriter();
			
			String id = request.getParameter("id");
			String pneirong = request.getParameter("pneirong");
			
			
			
			
			
			Pingjia bean = pingjiaDao.selectBean(" where id= "+id);

		
			bean.setPneirong(pneirong);
			bean.setShijian(Util.getTime());
			bean.setStatus(1);
			
			
			pingjiaDao.insertBean(bean);
			
			
			
			writer.print("<script language=javascript>alert('操作成功');window.location.href='pingjiamethod!pingjialist5.action?did="+bean.getDingdan().getId()+"';</script>");
		}
		
		
		
		
		//删除评价操作
		public void pingjiadelete() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter writer =  this.getPrintWriter();
			
			Pingjia bean = pingjiaDao.selectBean(" where id= "
					+ request.getParameter("id"));
			
		
			
			pingjiaDao.deleteBean(bean);
			
			writer.print("<script language=javascript>alert('操作成功');window.location.href='pingjiamethod!pingjialist5.action?did="+bean.getDingdan().getId()+"';</script>");
		}
	
	
	
	//评价列表
	public String pingjialist() {
		HttpServletRequest request = this.getRequest();
		
		
		String pname = request.getParameter("pname");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

	
		
		if (pname != null && !"".equals(pname)) {

			sb.append(" product.pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("pname", pname);
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		sb.append("  user.id="+user.getId()+" and dingdan.status='已收货' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = pingjiaDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", pingjiaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "pingjiamethod!pingjialist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "pingjiamethod!pingjialist.action");
		request.setAttribute("url2", "pingjiamethod!pingjia");
		request.setAttribute("title", "我的评价");
		this.setUrl("pingjialist.jsp");
		return SUCCESS;

	}
	
	
	

	
	
	
	
	
	
	//评价列表
	public String pingjialist4() {
		HttpServletRequest request = this.getRequest();
		
		String orderid = request.getParameter("orderid");
		
		String pname = request.getParameter("pname");
		
		String username = request.getParameter("username");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

	
		if (orderid != null && !"".equals(orderid)) {

			sb.append(" dingdan.orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}
		
		if (pname != null && !"".equals(pname)) {

			sb.append(" product.pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("pname", pname);
		}
		
		if (username != null && !"".equals(username)) {

			sb.append(" dingdan.user.username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		

		sb.append("  status=1 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = pingjiaDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", pingjiaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "pingjiamethod!pingjialist4.action", "共有" + total + "条记录"));
		request.setAttribute("url", "pingjiamethod!pingjialist4.action");
		request.setAttribute("url2", "pingjiamethod!pingjia");
		request.setAttribute("title", "评价管理");
		this.setUrl("manage/pingjia/pingjialist4.jsp");
		return SUCCESS;

	}
	
	
	//删除评价操作
	public void pingjiadelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Pingjia bean = pingjiaDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
	
		
		pingjiaDao.deleteBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='pingjiamethod!pingjialist4.action';</script>");
	}
	
}
