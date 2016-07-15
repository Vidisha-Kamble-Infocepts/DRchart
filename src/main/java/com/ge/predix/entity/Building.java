package com.ge.predix.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fact_building_demand")
public class Building implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Timestamp time;
	private long siteelectricalconsumption;
	private long siteelectricaldemand;
	private Timestamp insertdate;

	@ManyToOne
	@JoinColumn(name="buildingId")
	private DimBuilding dimBuilding;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	/*public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}*/
	public long getSiteelectricalconsumption() {
		return siteelectricalconsumption;
	}
	public void setSiteelectricalconsumption(long siteelectricalconsumption) {
		this.siteelectricalconsumption = siteelectricalconsumption;
	}
	public long getSiteelectricaldemand() {
		return siteelectricaldemand;
	}
	public void setSiteelectricaldemand(long siteelectricaldemand) {
		this.siteelectricaldemand = siteelectricaldemand;
	}
	public Timestamp getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(Timestamp insertdate) {
		this.insertdate = insertdate;
	}

	public DimBuilding getDimBuilding() {
		return dimBuilding;
	}

	public void setDimBuilding(DimBuilding dimBuilding) {
		this.dimBuilding = dimBuilding;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
