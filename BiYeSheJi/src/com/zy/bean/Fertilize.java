package com.zy.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_fertilize")
public class Fertilize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20)
	private String kind;// 施肥种类
	
	@Column(length = 20)
	private String add_time;// 施肥时间
	
	@Column(length = 40)
	private String remark;// 备注信息
	
	/*************** 扩展属性 ********************/
	@ManyToOne
	@JoinColumn(name = "earth_id")
	private Earth earth = new Earth();

	/********************set and get****************/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Earth getEarth() {
		return earth;
	}

	public void setEarth(Earth earth) {
		this.earth = earth;
	}
}
