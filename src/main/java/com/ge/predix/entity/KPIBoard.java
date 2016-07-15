package com.ge.predix.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fact_site_kpi")
public class KPIBoard implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String reportname;
	private String kpiName;
	private Long values;
	private Timestamp insertdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public Timestamp getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Timestamp insertdate) {
		this.insertdate = insertdate;
	}

	public Long getValues() {
		return values;
	}

	public void setValues(Long values) {
		this.values = values;
	}

}
