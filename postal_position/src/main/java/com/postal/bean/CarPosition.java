package com.postal.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="car_position",catalog="postal")
public class CarPosition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "lng",precision=4,scale=14)
	private Double lng;
	@Column(name = "lat",precision=4,scale=14)
	private Double lat;
	@Column(name = "intime")
	private Date intime;
	@Column(name = "plate_no",length=16)
	private String plateNo;
	@Column(name = "speed",length=16)
	private String speed;
	/**
	 * 航向
	 */
	@Column(name = "direction",scale=14)
	private String direction;
	/**
	 * 高度
	 */
	@Column(name = "hight",scale=14)
	private String hight;
	@Column(name = "status",scale=14)
	private String status;
	/**
	 * 扩展
	 */
	@Column(name = "extend",scale=200)
	private String extend;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public String getPlateNo() {
		return plateNo==null?"":plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getHight() {
		return hight;
	}
	public void setHight(String hight) {
		this.hight = hight;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	
}
