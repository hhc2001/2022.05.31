package com.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.UserDao;
import com.model.User;
import com.util.Pager;
import com.util.Util;


public class UserAction extends BaseAction {


	
	private static final long serialVersionUID = 1L;
	
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
//管理员后台登录
	public String login() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and role=1");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("manage", user);
			this.setUrl("manage/index.jsp");
			return "redirect";
		} else {
			writer.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='manage/login.jsp';</script>");
		}
		return null;
	}
//管理员后台退出
	public String loginout() {
		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("manage");
		this.setUrl("manage/login.jsp");
		return SUCCESS;
	}
	
//跳转到管理员后台修改密码页面
	public String changepwd() {
		this.setUrl("manage/password.jsp");
		return SUCCESS;
	}
	
//管理员后台修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("manage");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			writer.print("<script language=javascript>alert('修改成功');window.location.href='usermethod!changepwd.action';</script>");
		}else{
			writer.print("<script language=javascript>alert('原密码错误');window.location.href='usermethod!changepwd.action';</script>");
		}
	}
	
	
	//跳转到新用户注册页面
	public String register() throws Exception{
		HttpServletRequest request = this.getRequest();
		
		
		request.setAttribute("title", "新用户注册");
		
		request.setAttribute("url", "usermethod!register2.action");
		
		this.setUrl("register.jsp");
		return SUCCESS;
		
	}
	
	
	
	
	//新用户注册操作
	public void register2() throws Exception{
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String username = request.getParameter("username");
		
		User bean = userDao.selectBean(" where username='"+username+"'  and deletestatus=0 ");
		
		if(bean!=null){
			
			writer.print("<script language=javascript>alert('该用户名已经存在，注册失败');window.location.href='usermethod!register.action';</script>");

			return;
		}
		
		bean = new User();
		String password = request.getParameter("password");
		String truename = request.getParameter("truename");
		String phone = request.getParameter("phone");

		
		bean.setCtime(Util.getTime());
		bean.setPassword(password);
		bean.setPhone(phone);
		bean.setRole(2);
		bean.setTruename(truename);
		bean.setUsername(username);
		bean.setPhoto("photo.jpg");
		
		
		userDao.insertBean(bean);
		
		
		writer.print("<script language=javascript>alert('注册成功');window.location.href='usermethod!login3.action';</script>");
	}
	
	
	//跳转到用户登录页面
	public String login3() throws Exception{
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("title", "用户登录");
		
		request.setAttribute("url", "usermethod!login4.action");
		
		this.setUrl("login.jsp");
		return SUCCESS;
		
	}
	
	
	
	//用户登录
	public String login4() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and deletestatus=0 and role=2  ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			writer.print("<script language=javascript>window.location.href='index.action';</script>");
		} else {
			writer.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='usermethod!login3.action';</script>");
		}
		return null;
	}
	
	
//用户退出
	public void loginout2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		writer.print("<script language=javascript>alert('退出成功');window.location.href='index.action';</script>");
	}
	
	
	
	//注册用户列表
	public String userlist() {
		HttpServletRequest request = this.getRequest();
		String username = request.getParameter("username");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		

		sb.append("   deletestatus=0 and role=2 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "usermethod!userlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "usermethod!userlist.action");
		request.setAttribute("url2", "usermethod!user");
		request.setAttribute("title", "注册用户管理");
		this.setUrl("manage/user/userlist.jsp");
		return SUCCESS;

	}
	
	
	//删除注册用户操作
	public void userdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer =  this.getPrintWriter();
		
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='usermethod!userlist.action';</script>");
	}
	
	
	//我的信息页面
	public String userupdate() throws Exception{
		HttpServletRequest request = this.getRequest();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		User bean = userDao.selectBean(" where id= "+user.getId());
		
		request.setAttribute("bean", bean);
		
		request.setAttribute("title", "我的信息");
		
		request.setAttribute("url", "usermethod!userupdate2.action?id="+bean.getId());
		
		this.setUrl("userupdate.jsp");
		return SUCCESS;
		
	}
	
	private File uploadfile;
	

	public File getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
	//修改我的信息册操作
	public void userupdate2() throws Exception{
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String phone = request.getParameter("phone");
		String truename = request.getParameter("truename");
		
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		
		bean.setPhone(phone);
		bean.setTruename(truename);
		
		
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";

			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();

			String imgpath = time + ".jpg";
			File file1 = new File(savaPath + imgpath);
			Util.copyFile(uploadfile, file1);
			
			bean.setPhoto(imgpath);
		}
		
		userDao.updateBean(bean);
		
		
		writer.print("<script language=javascript>alert('操作成功');window.location.href='usermethod!userupdate.action';</script>");
	}
	
	
	//修改密码页面
	public String userupdate3() throws Exception{
		HttpServletRequest request = this.getRequest();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		User bean = userDao.selectBean(" where id= "+user.getId());
		
		request.setAttribute("bean", bean);
		
		request.setAttribute("title", "修改密码");
		
		request.setAttribute("url", "usermethod!userupdate4.action?id="+bean.getId());
		
		this.setUrl("userupdate3.jsp");
		return SUCCESS;
		
	}
	
	
	
	
	//修改我的信息册操作
	public void userupdate4() throws Exception{
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		
		
		if(bean.getPassword().equals(password1)){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			
			
			writer.print("<script language=javascript>alert('操作成功');window.location.href='usermethod!userupdate3.action';</script>");
		}else{
			writer.print("<script language=javascript>alert('操作失败，原密码错误');window.location.href='usermethod!userupdate3.action';</script>");
		}
		
		
		
	}
	
	
}
