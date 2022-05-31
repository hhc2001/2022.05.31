package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//评价
@Entity
@Table(name="t_Pingjia")
public class Pingjia {

	@Id
	@GeneratedValue
	private int id;//主键
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;//评价的用户，外键
	
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;//评价的商品
	
	@ManyToOne
	@JoinColumn(name="dingdanid")
	private Dingdan dingdan;//关联的订单，外键
	
	@Column(name="pneirong", columnDefinition="TEXT")
	private String pneirong;//评价内容
	
	private String shijian;//评价时间

	private int status;//是否评价  
	
	private int sl;//购买数量
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Dingdan getDingdan() {
		return dingdan;
	}

	public void setDingdan(Dingdan dingdan) {
		this.dingdan = dingdan;
	}

	public String getPneirong() {
		return pneirong;
	}

	public void setPneirong(String pneirong) {
		this.pneirong = pneirong;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}
	
	
	
	
	
	
	
	
}
