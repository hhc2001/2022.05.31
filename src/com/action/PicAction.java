package com.action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.PicDao;
import com.model.Pic;
import com.util.Pager;
import com.util.Util;


public class PicAction extends BaseAction {

	private static final long serialVersionUID = -4304509122548259589L;

	
	
	private PicDao picDao;
	

	
	
	
	public PicDao getPicDao() {
		return picDao;
	}

	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}

	
	
	//首页展示图片列表
	public String piclist() {
		HttpServletRequest request = this.getRequest();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		
		
	
		
		sb.append("   deletestatus=0  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = picDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", picDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "picmethod!piclist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "picmethod!piclist.action");
		request.setAttribute("url2", "picmethod!pic");
		request.setAttribute("title", "首页展示图片管理");
		this.setUrl("manage/pic/piclist.jsp");
		return SUCCESS;

	}

	private File uploadfile;
	

	public File getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
//跳转到更新首页展示图片页面
	public String picupdate() {
		HttpServletRequest request = this.getRequest();
		Pic bean = picDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "picmethod!picupdate2.action?id="+bean.getId());
		request.setAttribute("title", "首页展示图片更换");
		this.setUrl("manage/pic/picupdate.jsp");
		return SUCCESS;
	}
	
	
//首页展示图片更换操作
	public void picupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		
		
		Pic bean = picDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		
		
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";

			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();

			String imgpath = time + ".jpg";
			File file1 = new File(savaPath + imgpath);
			Util.copyFile(uploadfile, file1);
			
			bean.setImgpath(imgpath);
		}
		
		picDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='picmethod!piclist.action';</script>");
		
	}
	
		
}
