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
@Table(name="fact_asset_demand")
public class Asset implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//private int assetId;
	private int scAssetControl;
	private int ssAssetControl;
	private long assetelectricaldemand;
	private long assetconsumption;
	private long receivedsetpoint;
	private long assetuppercontrollimit;
	private long assetlowercontrollimit;
	private Timestamp insertdate;
	private Timestamp assetTime;
	@ManyToOne
	@JoinColumn(name="assetId")
	private DimAsset dimAsset;


	public int getScAssetControl() {
		return scAssetControl;
	}

	public void setScAssetControl(int scAssetControl) {
		this.scAssetControl = scAssetControl;
	}

	public int getSsAssetControl() {
		return ssAssetControl;
	}

	public void setSsAssetControl(int ssAssetControl) {
		this.ssAssetControl = ssAssetControl;
	}

	public long getAssetelectricaldemand() {
		return assetelectricaldemand;
	}

	public void setAssetelectricaldemand(long assetelectricaldemand) {
		this.assetelectricaldemand = assetelectricaldemand;
	}

	public long getAssetconsumption() {
		return assetconsumption;
	}

	public void setAssetconsumption(long assetconsumption) {
		this.assetconsumption = assetconsumption;
	}

	public long getReceivedsetpoint() {
		return receivedsetpoint;
	}

	public void setReceivedsetpoint(long receivedsetpoint) {
		this.receivedsetpoint = receivedsetpoint;
	}

	public long getAssetuppercontrollimit() {
		return assetuppercontrollimit;
	}

	public void setAssetuppercontrollimit(long assetuppercontrollimit) {
		this.assetuppercontrollimit = assetuppercontrollimit;
	}

	public long getAssetlowercontrollimit() {
		return assetlowercontrollimit;
	}

	public void setAssetlowercontrollimit(long assetlowercontrollimit) {
		this.assetlowercontrollimit = assetlowercontrollimit;
	}

	public Timestamp getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Timestamp insertdate) {
		this.insertdate = insertdate;
	}

	public Timestamp getAssetTime() {
		return assetTime;
	}

	public void setAssetTime(Timestamp assetTime) {
		this.assetTime = assetTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DimAsset getDimAsset() {
		return dimAsset;
	}

	public void setDimAsset(DimAsset dimAsset) {
		this.dimAsset = dimAsset;
	}

}
