package com.factory.ab.job;

public interface IJob {

	public boolean schedule();

	public boolean stop();

	public boolean start();

	public boolean shoudAlertMe();

}
