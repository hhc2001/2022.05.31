package com.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import com.dao.GonggaoDao;
import com.model.Gonggao;
import com.util.Pager;
import com.util.Util;


public class GonggaoAction extends BaseAction {

	private static final long serialVersionUID = -4304509122548259589L;

	
	private GonggaoDao gonggaoDao;

	
	public GonggaoDao getGonggaoDao() {
		return gonggaoDao;
	}
	public void setGonggaoDao(GonggaoDao gonggaoDao) {
		this.gonggaoDao = gonggaoDao;
	}
	
	
	//公告列表
	public String gonggaolist() {
		HttpServletRequest request = this.getRequest();
		String gtitle = request.getParameter("gtitle");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (gtitle != null && !"".equals(gtitle)) {

			sb.append("gtitle like '%" + gtitle + "%'");
			sb.append(" and ");
			request.setAttribute("gtitle", gtitle);
		}
		

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", gonggaoDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "gonggaomethod!gonggaolist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "gonggaomethod!gonggaolist.action");
		request.setAttribute("url2", "gonggaomethod!gonggao");
		request.setAttribute("title", "公告管理");
		this.setUrl("manage/gonggao/gonggaolist.jsp");
		return SUCCESS;

	}
//跳转到添加公告页面
	public String gonggaoadd() {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("url", "gonggaomethod!gonggaoadd2.action");
		request.setAttribute("title", "公告添加");
		this.setUrl("manage/gonggao/gonggaoadd.jsp");
		return SUCCESS;
	}
//添加公告操作
	public void gonggaoadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		String gcontent = request.getParameter("gcontent");
		
		String gtitle = request.getParameter("gtitle");
		

		Gonggao bean = new Gonggao();
		
		
		bean.setCtime(Util.getTime());
		bean.setGcontent(gcontent);
		bean.setGtitle(gtitle);
		
		gonggaoDao.insertBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='gonggaomethod!gonggaolist.action';</script>");
	}
	
	
//跳转到更新公告页面
	public String gonggaoupdate() {
		HttpServletRequest request = this.getRequest();
		Gonggao bean = gonggaoDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "gonggaomethod!gonggaoupdate2.action?id="+bean.getId());
		request.setAttribute("title", "公告信息修改");
		this.setUrl("manage/gonggao/gonggaoupdate.jsp");
		return SUCCESS;
	}
	
	
//更新公告操作
	public void gonggaoupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		String gcontent = request.getParameter("gcontent");
		
		String gtitle = request.getParameter("gtitle");
		
		Gonggao bean = gonggaoDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setGcontent(gcontent);
		bean.setGtitle(gtitle);
		
		gonggaoDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='gonggaomethod!gonggaolist.action';</script>");
		
	}
	
	//删除公告操作
	public void gonggaodelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Gonggao bean = gonggaoDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		gonggaoDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='gonggaomethod!gonggaolist.action';</script>");
	}
	
	
	//跳转到查看公告页面
	public String gonggaoupdate3() {
		HttpServletRequest request = this.getRequest();
		Gonggao bean = gonggaoDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "公告信息查看");
		this.setUrl("manage/gonggao/gonggaoupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	//公告列表
	public String gonggaolist2() {
		HttpServletRequest request = this.getRequest();
		String gtitle = request.getParameter("gtitle");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (gtitle != null && !"".equals(gtitle)) {

			sb.append("gtitle like '%" + gtitle + "%'");
			sb.append(" and ");
			request.setAttribute("gtitle", gtitle);
		}
		

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = gonggaoDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", gonggaoDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "gonggaomethod!gonggaolist2.action", "共有" + total + "条记录"));
		request.setAttribute("url", "gonggaomethod!gonggaolist2.action");
		request.setAttribute("url2", "gonggaomethod!gonggao");
		request.setAttribute("title", "商城公告");
		this.setUrl("gonggaolist2.jsp");
		return SUCCESS;

	}
	
	
	//跳转到查看公告页面
	public String gonggaoshow() {
		HttpServletRequest request = this.getRequest();
		Gonggao bean = gonggaoDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "公告信息查看");
		this.setUrl("gonggaoshow.jsp");
		return SUCCESS;
	}
	
}
