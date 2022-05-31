package com.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dao.FenleiDao;
import com.dao.PicDao;
import com.dao.ProductDao;
import com.model.Fenlei;
import com.util.Util;








public class IndexAction extends BaseAction {

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
	
	private PicDao picDao;



	public PicDao getPicDao() {
		return picDao;
	}




	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}




	//网站首页
	public String index() throws Exception{
		HttpServletRequest request = this.getRequest();
		Util.init(request);
		//所有分类
		request.setAttribute("fenleilist", fenleiDao.selectBeanList(0, 11, " where deletestatus=0 order by id desc  "));
		
		
		//精选推荐
		request.setAttribute("tuijianlist", productDao.selectBeanList(0, 5, " where deletestatus=0 and  tuijian='已推荐'  order by id desc"));

		//销量排行
		request.setAttribute("xllist", productDao.selectBeanList(0, 5, " where deletestatus=0   order by xl desc"));
		
		
		List<Fenlei> fenleilist = fenleiDao.selectBeanList(0, 4, " where deletestatus=0 order by id desc  ");
		
		
		if(fenleilist.size()>0){
			request.setAttribute("fenlei1", fenleilist.get(0).getFname());
			request.setAttribute("fenlei1id", fenleilist.get(0).getId());
			
			request.setAttribute("productlist1", productDao.selectBeanList(0, 6, " where deletestatus=0 and fenlei.id="+fenleilist.get(0).getId()+"  order by id desc"));
		}
		
		if(fenleilist.size()>1){
			request.setAttribute("fenlei2", fenleilist.get(1).getFname());
			request.setAttribute("fenlei2id", fenleilist.get(1).getId());
			
			request.setAttribute("productlist2", productDao.selectBeanList(0, 6, " where deletestatus=0 and fenlei.id="+fenleilist.get(1).getId()+"  order by id desc"));
		}
		
		if(fenleilist.size()>2){
			request.setAttribute("fenlei3", fenleilist.get(2).getFname());
			request.setAttribute("fenlei3id", fenleilist.get(2).getId());
			
			request.setAttribute("productlist3", productDao.selectBeanList(0, 6, " where deletestatus=0 and fenlei.id="+fenleilist.get(2).getId()+"  order by id desc"));
		}
		
		if(fenleilist.size()>3){
			request.setAttribute("fenlei4", fenleilist.get(3).getFname());
			request.setAttribute("fenlei4id", fenleilist.get(3).getId());
			
			request.setAttribute("productlist4", productDao.selectBeanList(0, 6, " where deletestatus=0 and fenlei.id="+fenleilist.get(3).getId()+"  order by id desc"));
		}
		
		
		request.setAttribute("pic",picDao.selectBean(" where id=1 "));
		
		
		return "success";
	}
	
	
		
		
		
		
}
