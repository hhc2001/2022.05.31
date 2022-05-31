package com.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dao.DingdanDao;
import com.dao.GouwucheDao;
import com.dao.PingjiaDao;
import com.dao.ProductDao;
import com.model.Dingdan;
import com.model.Gouwuche;
import com.model.Pingjia;
import com.model.Product;
import com.model.User;
import com.util.Arith;
import com.util.Pager;
import com.util.Util;


public class DingdanAction extends BaseAction {

	private static final long serialVersionUID = -4304509122548259589L;

	
	
	private DingdanDao dingdanDao;
	
	
	public DingdanDao getDingdanDao() {
		return dingdanDao;
	}
	public void setDingdanDao(DingdanDao dingdanDao) {
		this.dingdanDao = dingdanDao;
	}
	
	
	private GouwucheDao gouwucheDao;

	public GouwucheDao getGouwucheDao() {
		return gouwucheDao;
	}
	public void setGouwucheDao(GouwucheDao gouwucheDao) {
		this.gouwucheDao = gouwucheDao;
	}

	private ProductDao productDao;

	
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	//订单列表
	public String dingdanlist() {
		HttpServletRequest request = this.getRequest();
		String orderid = request.getParameter("orderid");
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}
		
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		sb.append("   deletestatus=0 and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = dingdanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", dingdanDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "dingdanmethod!dingdanlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "dingdanmethod!dingdanlist.action");
		request.setAttribute("url2", "dingdanmethod!dingdan");
		request.setAttribute("title", "我的订单");
		this.setUrl("dingdanlist.jsp");
		return SUCCESS;

	}
	
	
//跳转到生成订单页面
	public String dingdanadd() {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		request.setAttribute("url", "dingdanmethod!dingdanadd2.action");
		request.setAttribute("title", "生成订单");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		List<Gouwuche> list = gouwucheDao.selectBeanList(0, 9999, " where  user.id="+user.getId());
		
		if(list.size()<=0){
			
			writer.print("<script language=javascript>alert('购物车没有商品，请添加商品');window.location.href='gouwuchemethod!gouwuchelist.action';</script>");
			return null;
		}
		
		for(Gouwuche bean:list){
			Product pro = productDao.selectBean(" where id= "+bean.getProduct().getId());
			
			if(pro.getKucun()<bean.getShuliang()){
				writer.print("<script language=javascript>alert('商品库存不足，购买失败');window.location.href='gouwuchemethod!gouwuchelist.action';</script>");
				return null;
			}
			
			
			
		}
		
		double zongjia = 0;
		for(Gouwuche bean:list){
			zongjia = Arith.add(zongjia, Arith.mul(bean.getJiage(), bean.getShuliang()));
		}
		
		request.setAttribute("zongjia", zongjia);
		
		this.setUrl("dingdanadd.jsp");
		return SUCCESS;
	}
	
	
	private PingjiaDao pingjiaDao;

	
	public PingjiaDao getPingjiaDao() {
		return pingjiaDao;
	}
	public void setPingjiaDao(PingjiaDao pingjiaDao) {
		this.pingjiaDao = pingjiaDao;
	}
	
	
	
	
	//生成订单操作
	public void dingdanadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();

		String sjname = request.getParameter("sjname");
		String phone = request.getParameter("phone");
		String beizhu = request.getParameter("beizhu");
		String address =request.getParameter("address");
		
		String w =request.getParameter("w");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		String order_id_ = "";
		
		int order_id = dingdanDao.selectBeanCount("")+1;
		if(order_id<10){
			order_id_ = "00000"+order_id;
		}else if(order_id<100){
			order_id_ = "0000"+order_id;
		}else if(order_id<1000){
			order_id_ = "000"+order_id;
		}else if(order_id<10000){
			order_id_ = "00"+order_id;
		}else if(order_id<100000){
			order_id_ = "0"+order_id;
		}else if(order_id<1000000){
			order_id_ = ""+order_id;
		}
		
		
		Dingdan bean = new Dingdan();
		
		bean.setAddress(address);
		bean.setBeizhu(beizhu);
		bean.setCreatetime(Util.getTime());
	    bean.setDeletestatus(0);
		bean.setOrderid(order_id_);
		bean.setPhone(phone);
		bean.setSjname(sjname);
		bean.setStatus("处理中");
		bean.setUser(user);
		bean.setPay(w);
	
		dingdanDao.insertBean(bean);
		
		List<Gouwuche> list = gouwucheDao.selectBeanList(0, 9999, " where user.id="+user.getId());
		StringBuffer sb = new StringBuffer();
		double zongjia = 0;
		for(Gouwuche g:list){
			double price = 0;
			
			price = g.getProduct().getJiage();
			
			
			sb.append(" 商品名:"+g.getProduct().getPname() +",购买数量:"+g.getShuliang()  +",单价"+price 
					+",￥小计"+ Arith.mul(g.getShuliang(), price)+"<br/>") ;
			
			Product product = g.getProduct();
			
			product.setXl(product.getXl()+g.getShuliang());
			
			product.setKucun(product.getKucun()-g.getShuliang());
			
			productDao.updateBean(product);
			
	
			gouwucheDao.deleteBean(g);
			
			
			
			zongjia = zongjia+(Arith.mul(g.getShuliang(), price));
			
			Pingjia pj = new Pingjia();
			pj.setDingdan(bean);
			pj.setProduct(product);
			pj.setUser(user);
			pj.setSl(g.getShuliang());
			
			pingjiaDao.insertBean(pj);
		
			
		}
		
		
		bean.setXiangqing(sb.toString());
		bean.setZongjia(zongjia);
		
		dingdanDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='dingdanmethod!dingdanlist.action';</script>");
	}
	
	
	//取消订单操作
	public void dingdandelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Dingdan bean = dingdanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setStatus("已取消");
		
		
		dingdanDao.updateBean(bean);
		
		List<Pingjia> list = pingjiaDao.selectBeanList(0, 9999, " where dingdan.id= "+bean.getId());
		
		for(Pingjia pj:list){
			
			Product pro = productDao.selectBean(" where id= "+pj.getProduct().getId());
			
			pro.setKucun(pro.getKucun()+pj.getSl());
			
			productDao.updateBean(pro);
			
			
		}
		
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='dingdanmethod!dingdanlist.action';</script>");
	}
	
	
	
	
	
	//跳转到查看订单页面
	public String dingdanupdate3() {
		HttpServletRequest request = this.getRequest();
		Dingdan bean = dingdanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "订单信息查看");
		this.setUrl("dingdanupdate3.jsp");
		return SUCCESS;
	}
	
	
	//订单列表
	public String dingdanlist2() {
		HttpServletRequest request = this.getRequest();
		String orderid = request.getParameter("orderid");
		
		String sjname = request.getParameter("sjname");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}
		
		if (sjname != null && !"".equals(sjname)) {

			sb.append(" sjname like '%" + sjname + "%'");
			sb.append(" and ");
			request.setAttribute("sjname", sjname);
		}
		

		sb.append("   deletestatus=0  and status='处理中' order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = dingdanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", dingdanDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "dingdanmethod!dingdanlist2.action", "共有" + total + "条记录"));
		request.setAttribute("url", "dingdanmethod!dingdanlist2.action");
		request.setAttribute("url2", "dingdanmethod!dingdan");
		request.setAttribute("title", "待处理订单");
		this.setUrl("manage/dingdan/dingdanlist2.jsp");
		return SUCCESS;

	}
	
	
	//发货操作
	public void dingdandelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Dingdan bean = dingdanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setStatus("已发货");
		
		
		dingdanDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='dingdanmethod!dingdanlist2.action';</script>");
	}
	
	
	//跳转到查看订单页面
	public String dingdanupdate5() {
		HttpServletRequest request = this.getRequest();
		Dingdan bean = dingdanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "订单信息查看");
		this.setUrl("manage/dingdan/dingdanupdate5.jsp");
		return SUCCESS;
	}
	
	
	//订单列表
	public String dingdanlist3() {
		HttpServletRequest request = this.getRequest();
		String orderid = request.getParameter("orderid");
		
		String sjname = request.getParameter("sjname");
		
		String status = request.getParameter("status");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}
		
		if (sjname != null && !"".equals(sjname)) {

			sb.append(" sjname like '%" + sjname + "%'");
			sb.append(" and ");
			request.setAttribute("sjname", sjname);
		}
		
		if (status != null && !"".equals(status)) {

			sb.append(" status like '%" + status + "%'");
			sb.append(" and ");
			request.setAttribute("status", status);
		}
		
		

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = dingdanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", dingdanDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "dingdanmethod!dingdanlist3.action", "共有" + total + "条记录"));
		request.setAttribute("url", "dingdanmethod!dingdanlist3.action");
		request.setAttribute("url2", "dingdanmethod!dingdan");
		request.setAttribute("title", "订单查询");
		this.setUrl("manage/dingdan/dingdanlist3.jsp");
		return SUCCESS;

	}
	
	
	
	
	//确认收货操作
	public void dingdandelete3() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Dingdan bean = dingdanDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setStatus("已收货");
		
		
		dingdanDao.updateBean(bean);
		
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='dingdanmethod!dingdanlist.action';</script>");
	}
	
}
