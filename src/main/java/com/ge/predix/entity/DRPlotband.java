package com.ge.predix.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fact_asset_plotband")
public class DRPlotband implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String reportname;
	private int assetId;
	private String plotband;
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp insertdate;
	private Timestamp updatedate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}


	public String getPlotband() {
		return plotband;
	}
	public void setPlotband(String plotband) {
		this.plotband = plotband;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public Timestamp getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(Timestamp insertdate) {
		this.insertdate = insertdate;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

}

