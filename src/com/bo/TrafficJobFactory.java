package com.bo;

import com.factory.ab.job.IJob;

public class TrafficJobFactory extends JobFactory {

	String von;
	String bis;

	public TrafficJobFactory(String vonLocation, String bisLocation) {
		super();
		this.von = vonLocation;
		this.bis = bisLocation;
	}

	protected String getVon() {
		return von;
	}

	protected void setVon(String von) {
		this.von = von;
	}

	protected String getBis() {
		return bis;
	}

	protected void setBis(String bis) {
		this.bis = bis;
	}

	@Override
	public IJob createJob() throws Exception {
		// TODO Auto-generated method stub
		return new TrafficJob(getVon(), getBis());
	}

}
