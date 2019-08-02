package com.ybjt.location.bean;

import java.util.Date;

/**
 * #城市实体类
 * @author Administrator
 *
 */
public class Location {
	private String LOCATION_ID;//id主键
	private String LOCATION_NAME;//名称
	private Date LOCATION_CREAETIME; //创建时间
	private Date LOCATION_EDITTIME;//编辑时间
	private String LOCATION_PID;//父id
	private String LOCATION_DESC;//描述
	
	//get、set方法
	public String getLOCATION_ID() {
		return LOCATION_ID;
	}
	public void setLOCATION_ID(String lOCATION_ID) {
		LOCATION_ID = lOCATION_ID;
	}
	public String getLOCATION_NAME() {
		return LOCATION_NAME;
	}
	public void setLOCATION_NAME(String lOCATION_NAME) {
		LOCATION_NAME = lOCATION_NAME;
	}
	public Date getLOCATION_CREAETIME() {
		return LOCATION_CREAETIME;
	}
	public void setLOCATION_CREAETIME(Date lOCATION_CREAETIME) {
		LOCATION_CREAETIME = lOCATION_CREAETIME;
	}
	public Date getLOCATION_EDITTIME() {
		return LOCATION_EDITTIME;
	}
	public void setLOCATION_EDITTIME(Date lOCATION_EDITTIME) {
		LOCATION_EDITTIME = lOCATION_EDITTIME;
	}
	public String getLOCATION_PID() {
		return LOCATION_PID;
	}
	public void setLOCATION_PID(String lOCATION_PID) {
		LOCATION_PID = lOCATION_PID;
	}
	public String getLOCATION_DESC() {
		return LOCATION_DESC;
	}
	public void setLOCATION_DESC(String lOCATION_DESC) {
		LOCATION_DESC = lOCATION_DESC;
	}
}
