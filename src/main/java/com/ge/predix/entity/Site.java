package com.ge.predix.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fact_site_demand")
public class Site implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private Integer siteId;
	private Timestamp siteTime;
	private Long historicalpeak;
	private Long siteestimatedelectricalpeak;
	private Long assetaverageelectricaldemand;
	private Long billingperiodpeak;
	private Long pdmexpectedsavings;
	private Long pdmactualsavings;
	private Long siteelectricalconsumption;
	private Long siteelectricaldemand;
	private Long siteLowercontrollimit;
	private Long offeredcapacity;
	private Long realtimecapacity;
	private Integer armed;
	private Integer assetoncontrol;
	private Integer billingcycleday;
	private Long siteReceivedsetpoint;
	private Long siteUppercontrollimit;
	private Timestamp insertdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public Timestamp getSiteTime() {
		return siteTime;
	}
	public void setSiteTime(Timestamp siteTime) {
		this.siteTime = siteTime;
	}
	public long getHistoricalpeak() {
		return historicalpeak;
	}
	public void setHistoricalpeak(long historicalpeak) {
		this.historicalpeak = historicalpeak;
	}
	public long getSiteestimatedelectricalpeak() {
		return siteestimatedelectricalpeak;
	}
	public void setSiteestimatedelectricalpeak(long siteestimatedelectricalpeak) {
		this.siteestimatedelectricalpeak = siteestimatedelectricalpeak;
	}
	public long getAssetaverageelectricaldemand() {
		return assetaverageelectricaldemand;
	}
	public void setAssetaverageelectricaldemand(long assetaverageelectricaldemand) {
		this.assetaverageelectricaldemand = assetaverageelectricaldemand;
	}
	public long getBillingperiodpeak() {
		return billingperiodpeak;
	}
	public void setBillingperiodpeak(long billingperiodpeak) {
		this.billingperiodpeak = billingperiodpeak;
	}
	public long getPdmexpectedsavings() {
		return pdmexpectedsavings;
	}
	public void setPdmexpectedsavings(long pdmexpectedsavings) {
		this.pdmexpectedsavings = pdmexpectedsavings;
	}
	public long getPdmactualsavings() {
		return pdmactualsavings;
	}
	public void setPdmactualsavings(long pdmactualsavings) {
		this.pdmactualsavings = pdmactualsavings;
	}
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
	public long getSiteLowercontrollimit() {
		return siteLowercontrollimit;
	}
	public void setSiteLowercontrollimit(long siteLowercontrollimit) {
		this.siteLowercontrollimit = siteLowercontrollimit;
	}
	public long getOfferedcapacity() {
		return offeredcapacity;
	}
	public void setOfferedcapacity(long offeredcapacity) {
		this.offeredcapacity = offeredcapacity;
	}
	public long getRealtimecapacity() {
		return realtimecapacity;
	}
	public void setRealtimecapacity(long realtimecapacity) {
		this.realtimecapacity = realtimecapacity;
	}
	public int getArmed() {
		return armed;
	}
	public void setArmed(int armed) {
		this.armed = armed;
	}
	public int getAssetoncontrol() {
		return assetoncontrol;
	}
	public void setAssetoncontrol(int assetoncontrol) {
		this.assetoncontrol = assetoncontrol;
	}
	public int getBillingcycleday() {
		return billingcycleday;
	}
	public void setBillingcycleday(int billingcycleday) {
		this.billingcycleday = billingcycleday;
	}
	public long getSiteReceivedsetpoint() {
		return siteReceivedsetpoint;
	}
	public void setSiteReceivedsetpoint(long siteReceivedsetpoint) {
		this.siteReceivedsetpoint = siteReceivedsetpoint;
	}
	public long getSiteUppercontrollimit() {
		return siteUppercontrollimit;
	}
	public void setSiteUppercontrollimit(long siteUppercontrollimit) {
		this.siteUppercontrollimit = siteUppercontrollimit;
	}
	public Timestamp getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(Timestamp insertdate) {
		this.insertdate = insertdate;
	}

}
