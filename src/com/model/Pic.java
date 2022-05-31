package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//首页展示图片
@Entity
@Table(name="t_Pic")
public class Pic {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//是否删除的标志，0表示正常，1表示删除
	
	private String imgpath;//图片地址

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	
	
	
	
}
