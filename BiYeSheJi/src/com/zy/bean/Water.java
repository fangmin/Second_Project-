package com.zy.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="t_water")
public class Water {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20)
	private String add_time;// 浇灌时间
	
	@Column(length = 40)
	private String remark;// 备注信息
	
	@Transient
	private String overTime_remind;//超时提醒
	
	
	/*************** 扩展属性 ********************/
	@ManyToOne
	@JoinColumn(name = "plant_id")
	private Plant plant = new Plant();

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

	@Transient
	public String getOverTime_remind() {
		return overTime_remind;
	}

	public void setOverTime_remind(String overTime_remind) {
		this.overTime_remind = overTime_remind;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
}
