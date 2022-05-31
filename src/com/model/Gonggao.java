package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//公告
@Entity
@Table(name="t_Gonggao")
public class Gonggao {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//是否删除的标志，0表示正常，1表示删除
	
	private String gtitle;//公告标题
	
	@Column(name="gcontent", columnDefinition="TEXT")
	private String gcontent;//公告内容
	
	private String ctime;//添加日期

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

	public String getGtitle() {
		return gtitle;
	}

	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}

	public String getGcontent() {
		return gcontent;
	}

	public void setGcontent(String gcontent) {
		this.gcontent = gcontent;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	
	
	
	
	
	
}
