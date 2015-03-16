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
@Table(name = "t_plant")
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20)
	private String kind;// 耕种种类

	@Column(length = 20)
	private String add_time;// 耕种时间

	@Column(length = 40)
	private String remark;// 备注信息

	/*************** 扩展属性 ********************/
	/*@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="plant")
	@JoinColumn(name = "earth_id")
	 private Set<Earth> earth = new HashSet<Earth>();*/
	
	@ManyToOne
	@JoinColumn(name="earth_id")
	private Earth earth = new Earth();
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
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

	public Earth getEarth() {
		return earth;
	}

	public void setEarth(Earth earth) {
		this.earth = earth;
	}

/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE },mappedBy ="plant")
	public Set<Earth> getEarth() {
		return earth;
	}

	public void setEarth(Set<Earth> earth) {
		this.earth = earth;
	}*/
	
}
