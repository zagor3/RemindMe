package com.bo.gmap.api;
import com.bo.TrafficJobFactory;
import com.factory.ab.job.IJob;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String start = "Obstweg 5,70771 Echterdingen";
		String ende = "Filderstrasse 29,70180 Stuttgart";

		try {

			IJob job = new TrafficJobFactory(start, ende).createJob();
			job.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}