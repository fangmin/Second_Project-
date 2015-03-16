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
@Table(name="t_food")
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition="DOUBLE")
	private double area;// 土地面积
	
	@Column(columnDefinition="DOUBLE")
	private double number;// 收货数量

	@Transient
	private double aver_product;//平均产量
	
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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

 
	@Transient
	public double getAver_product() {
		return aver_product;
	}

	@Transient
	public void setAver_product(double aver_product) {
		this.aver_product = aver_product;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}
}
