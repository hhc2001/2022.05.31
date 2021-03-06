package com.action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.FenleiDao;
import com.dao.PingjiaDao;
import com.dao.ProductDao;
import com.model.Product;
import com.util.Pager;
import com.util.Util;


public class ProductAction extends BaseAction {

	private static final long serialVersionUID = -4304509122548259589L;

	
	
	private ProductDao productDao;
	

	
	
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	private FenleiDao fenleiDao;

	public FenleiDao getFenleiDao() {
		return fenleiDao;
	}
	public void setFenleiDao(FenleiDao fenleiDao) {
		this.fenleiDao = fenleiDao;
	}
	
	
	
	
	
	
	//ๅๅๅ่กจ
	public String productlist() {
		HttpServletRequest request = this.getRequest();
		String pname = request.getParameter("pname");
		
		String fenleiid = request.getParameter("fenleiid");
		
		request.setAttribute("fenleilist", fenleiDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pname != null && !"".equals(pname)) {

			sb.append("pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("pname", pname);
		}
		
		if (fenleiid != null && !"".equals(fenleiid)) {

			sb.append(" fenlei.id = " + fenleiid + "");
			sb.append(" and ");
			request.setAttribute("fenleiid", fenleiid);
		}
		
	
		
		sb.append("   deletestatus=0  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "productmethod!productlist.action", "ๅฑๆ" + total + "ๆก่ฎฐๅฝ"));
		request.setAttribute("url", "productmethod!productlist.action");
		request.setAttribute("url2", "productmethod!product");
		request.setAttribute("title", "ๅๅ็ฎก็");
		this.setUrl("manage/product/productlist.jsp");
		return SUCCESS;

	}
	
//่ทณ่ฝฌๅฐๅๅธๅๅ้กต้ข
	public String productadd() {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("fenleilist", fenleiDao.selectBeanList(0, 9999, " where  deletestatus=0"));
		request.setAttribute("url", "productmethod!productadd2.action");
		request.setAttribute("title", "ๅๅธๅๅ");
		this.setUrl("manage/product/productadd.jsp");
		return SUCCESS;
	}
	
	
	
	private File uploadfile;
	

	public File getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	//ๆทปๅ?ๅๅๆไฝ
	public void productadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		
		String fid = request.getParameter("fid");
		String jiage = request.getParameter("jiage");
		String miaoshu = request.getParameter("miaoshu");
		String pname = request.getParameter("pname");
		

		Product bean = new Product();
		
		bean.setCreatetime(Util.getTime());
		bean.setFenlei(fenleiDao.selectBean(" where id= "+fid));

		bean.setJiage(Double.parseDouble(jiage));
		bean.setMiaoshu(miaoshu);
		bean.setPname(pname);
		bean.setTuijian("ๆชๆจ่");
		
		
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";

			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();

			String imgpath = time + ".jpg";
			File file1 = new File(savaPath + imgpath);
			Util.copyFile(uploadfile, file1);
			
			bean.setImgpath(imgpath);
		}
		
		
		productDao.insertBean(bean);

		writer.print("<script language=javascript>alert('ๆไฝๆๅ');window.location.href='productmethod!productlist.action';</script>");
	}
	
	
//่ทณ่ฝฌๅฐๆดๆฐๅๅ้กต้ข
	public String productupdate() {
		HttpServletRequest request = this.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("fenleilist", fenleiDao.selectBeanList(0, 9999, " where  deletestatus=0"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "productmethod!productupdate2.action?id="+bean.getId());
		request.setAttribute("title", "ๅๅไฟกๆฏไฟฎๆน");
		this.setUrl("manage/product/productupdate.jsp");
		return SUCCESS;
	}
	
	
//ๆดๆฐๅๅๆไฝ
	public void productupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		String fid = request.getParameter("fid");
		String jiage = request.getParameter("jiage");

		String miaoshu = request.getParameter("miaoshu");
		String pname = request.getParameter("pname");
		
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setFenlei(fenleiDao.selectBean(" where id= "+fid));

		bean.setJiage(Double.parseDouble(jiage));
		bean.setMiaoshu(miaoshu);
		bean.setPname(pname);
		
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";

			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();

			String imgpath = time + ".jpg";
			File file1 = new File(savaPath + imgpath);
			Util.copyFile(uploadfile, file1);
			
			bean.setImgpath(imgpath);
		}
		
		productDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('ๆไฝๆๅ');window.location.href='productmethod!productlist.action';</script>");
		
	}
	
	//ๅ?้คๅๅๆไฝ
	public void productdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		productDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('ๆไฝๆๅ');window.location.href='productmethod!productlist.action';</script>");
	}
	
	
	//่ทณ่ฝฌๅฐๆฅ็ๅๅ้กต้ข
	public String productupdate3() {
		HttpServletRequest request = this.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "ๅๅไฟกๆฏๆฅ็");
		this.setUrl("manage/product/productupdate3.jsp");
		return SUCCESS;
	}
	
	
	//ๆจ่ๅๅๆไฝ
	public void productdelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setTuijian("ๅทฒๆจ่");
		
		productDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('ๆไฝๆๅ');window.location.href='productmethod!productlist.action';</script>");
	}
	
	
	
	//ๆจ่ๅๅๆไฝ
	public void productdelete3() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setTuijian("ๆชๆจ่");
		
		productDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('ๆไฝๆๅ');window.location.href='productmethod!productlist.action';</script>");
	}
	
	
	
	//ๅๅฐๅๅๅ่กจ
	public String productlist2() {
		HttpServletRequest request = this.getRequest();
		String pname = request.getParameter("pname");
		
		String jiage1 = request.getParameter("jiage1");
		
		String jiage2 = request.getParameter("jiage2");
		
		String fenleiid = request.getParameter("fenleiid");
		
		String tuijian = request.getParameter("tuijian");
		
		request.setAttribute("fenleilist", fenleiDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pname != null && !"".equals(pname)) {

			sb.append("pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("pname", pname);
		}
		
		if (tuijian != null && !"".equals(tuijian)) {

			sb.append("tuijian like '%" + tuijian + "%'");
			sb.append(" and ");
			request.setAttribute("tuijian", tuijian);
		}
		
		if (fenleiid != null && !"".equals(fenleiid)) {

			sb.append(" fenlei.id = " + fenleiid + "");
			sb.append(" and ");
			request.setAttribute("fenleiid", fenleiid);
		}
		
		if (jiage1 != null && !"".equals(jiage1)) {

			sb.append(" jiage >=" + jiage1 + "");
			sb.append(" and ");
			request.setAttribute("jiage1", jiage1);
		}
		
		if (jiage2 != null && !"".equals(jiage2)) {

			sb.append(" jiage <=" + jiage2 + "");
			sb.append(" and ");
			request.setAttribute("jiage2", jiage2);
		}
		
		sb.append("   deletestatus=0  order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 20;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "productmethod!productlist2.action", "ๅฑๆ" + total + "ๆก่ฎฐๅฝ"));
		request.setAttribute("url", "productmethod!productlist2.action");
		request.setAttribute("url2", "productmethod!product");
		request.setAttribute("title", "ๅๅๅ่กจ");
		this.setUrl("productlist2.jsp");
		return SUCCESS;

	}
	
	
	private PingjiaDao pingjiaDao;

	
	public PingjiaDao getPingjiaDao() {
		return pingjiaDao;
	}
	public void setPingjiaDao(PingjiaDao pingjiaDao) {
		this.pingjiaDao = pingjiaDao;
	}
	
	
	//่ทณ่ฝฌๅฐๆฅ็ๅๅ้กต้ข
	public String productshow() {
		HttpServletRequest request = this.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setDj(bean.getDj()+1);
		
		productDao.updateBean(bean);
		
		request.setAttribute("bean", bean);
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		
		sb.append("    status=1 and product.id="+bean.getId()+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 3;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = pingjiaDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", pingjiaDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "productmethod!productshow.action?id="+bean.getId(), "ๅฑๆ" + total + "ๆก่ฎฐๅฝ"));
		
		request.setAttribute("title", "ๅๅไฟกๆฏๆฅ็");
		this.setUrl("productshow.jsp");
		return SUCCESS;
	}
	
	
	

	//่ทณ่ฝฌๅฐๅๅๅฅๅบ้กต้ข
		public String productupdate5() {
			HttpServletRequest request = this.getRequest();
			Product bean = productDao.selectBean(" where id= "
					+ request.getParameter("id"));
			request.setAttribute("fenleilist", fenleiDao.selectBeanList(0, 9999, " where  deletestatus=0"));
			request.setAttribute("bean", bean);
			request.setAttribute("url", "productmethod!productupdate6.action?id="+bean.getId());
			request.setAttribute("title", "ๅๅๅฅๅบ");
			this.setUrl("manage/product/productupdate5.jsp");
			return SUCCESS;
		}
		
		
	//ๆดๆฐๅๅๆไฝ
		public void productupdate6() throws IOException {
			HttpServletRequest request = this.getRequest();
			PrintWriter writer =  this.getPrintWriter();
			
			String kucun = request.getParameter("kucun");
			
			Product bean = productDao.selectBean(" where id= "
					+ request.getParameter("id"));
			
			bean.setKucun(bean.getKucun()+Integer.parseInt(kucun));
			
			productDao.updateBean(bean);
			
			writer.print("<script language=javascript>alert('ๆไฝๆๅ');window.location.href='productmethod!productlist.action';</script>");
			
		}
		
}
