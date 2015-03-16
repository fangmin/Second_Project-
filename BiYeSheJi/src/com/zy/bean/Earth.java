package com.zy.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_earth")
public class Earth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=20)
	private String earth_num;   //土地编号
	
	@Column(length=20)
	private String area;  //土地面积
	
	@Column(length=20)
	private String position;//土地位置

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEarth_num() {
		return earth_num;
	}

	public void setEarth_num(String earth_num) {
		this.earth_num = earth_num;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
