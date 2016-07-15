package com.ge.predix.service.csvreader;

/*
 * Java class representing the columns of the csv file
 * as the variables of the class.
 */
public class SampleBldg {
	private String DateTime;
	private String SiteElectricalConsumption;
	private String SiteElectricalDemand;
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getSiteElectricalConsumption() {
		return SiteElectricalConsumption;
	}
	public void setSiteElectricalConsumption(String siteElectricalConsumption) {
		SiteElectricalConsumption = siteElectricalConsumption;
	}
	public String getSiteElectricalDemand() {
		return SiteElectricalDemand;
	}
	public void setSiteElectricalDemand(String siteElectricalDemand) {
		SiteElectricalDemand = siteElectricalDemand;
	}
	@Override
	public String toString() {
		return "SampleBldg [DateTime=" + DateTime + ", SiteElectricalConsumption=" + SiteElectricalConsumption
				+ ", SiteElectricalDemand=" + SiteElectricalDemand + "]";
	}
}
