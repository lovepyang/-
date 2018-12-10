package com.postal.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etcar", catalog = "postal")
public class Etcar {//电动车
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "carbrand",length=64)
	private String carBrand;//车辆品牌及型号
	@Column(name = "enginemodel",length=32)
	private String engineModel;//发动机型号
	@Column(name = "carno",length=32)
	private String carNo;//车架号
	@Column(name = "carsize",length=64)
	private String carSize;//车辆尺寸
	@Column(name = "plateno",length=64)
	private String plateNo;//车牌号
	@Column(name = "compname",length=64)
	private String compName;//公司名
	@Column(name = "compid")
	private Integer compId;//公司ID
	@Column(name = "uid")
	private Integer uid;//申请账号ID
	@Column(name = "applyid")
	private Integer applyId;//申请ID
	@Column(name = "status")
	private Integer status;//状态  0:编辑状态  1提交审核  2审核通过
	@Column(name = "modstatus")
	private Integer modStatus;//车辆更新状态  0:编辑状态  1提交审核  2审核通过
	@Column(name = "servbrand",length=64)
	private String servBrand;//品牌名
	@Column(name = "servbrandcode",length=1)
	private String servBrandCode;//品牌码
	@Column(name = "servarea",length=64)
	private String servArea;//服务地区名
	@Column(name = "servareacode",length=2)
	private String servAreaCode;//服务地区码
	@Column(name = "intime")
	private Date intime;//插入时间
	@Column(name = "record")
	private Integer record;//是否备案  0没备案  1已经备案
	@Column(name = "lng",precision=4,scale=14)
	private Double lng;//经度
	@Column(name = "lat",precision=4,scale=14)
	private Double lat;//维度
	@Column(name = "gpstime")
	private Date gpstime;//gps上传时间
	@Column(name = "speed")
	private Integer speed;//速度m/s
	@Column(name = "battery")
	private Integer battery;//电池
	@Column(name = "reason",length=128)
	private String reason;//审核不通过原因
	@Column(name = "carmodapplyid")
	private Integer carModApplyId;//车辆更换申请Id
	@Column(name = "audituid")
	private Integer auditUid;//审核人ID
	@Column(name = "audituname",length=64)
	private String auditUname;//审核人名字
	@Column(name = "audittime")
	private Date auditTime;//审核时间
	@Column(name = "auditinfo",length=128)
	private String auditInfo;//审核信息
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getEngineModel() {
		return engineModel;
	}
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarSize() {
		return carSize;
	}
	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompId() {
		return compId;
	}
	public void setCompId(Integer compId) {
		this.compId = compId;
	}
	public String getServBrand() {
		return servBrand;
	}
	public void setServBrand(String servBrand) {
		this.servBrand = servBrand;
	}
	public String getServBrandCode() {
		return servBrandCode;
	}
	public void setServBrandCode(String servBrandCode) {
		this.servBrandCode = servBrandCode;
	}
	public String getServArea() {
		return servArea;
	}
	public void setServArea(String servArea) {
		this.servArea = servArea;
	}
	public String getServAreaCode() {
		return servAreaCode;
	}
	public void setServAreaCode(String servAreaCode) {
		this.servAreaCode = servAreaCode;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
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
	public Date getGpstime() {
		return gpstime;
	}
	public void setGpstime(Date gpstime) {
		this.gpstime = gpstime;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getBattery() {
		return battery;
	}
	public void setBattery(Integer battery) {
		this.battery = battery;
	}
	public Integer getModStatus() {
		return modStatus;
	}
	public void setModStatus(Integer modStatus) {
		this.modStatus = modStatus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getCarModApplyId() {
		return carModApplyId;
	}
	public void setCarModApplyId(Integer carModApplyId) {
		this.carModApplyId = carModApplyId;
	}
	public Integer getAuditUid() {
		return auditUid;
	}
	public void setAuditUid(Integer auditUid) {
		this.auditUid = auditUid;
	}
	public String getAuditUname() {
		return auditUname;
	}
	public void setAuditUname(String auditUname) {
		this.auditUname = auditUname;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
