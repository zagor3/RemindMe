package com.bo;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.bo.gmap.api.Leg;
import com.bo.gmap.api.Route;
import com.bo.gmap.api.Step;
import com.factory.ab.job.IJob;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.util.HttpUtil;

public class TrafficJob implements IJob {

	String locationVon;
	String locationBis;
	int duration;

	String jsonFile;

	protected TrafficJob(String locationVon, String locationBis) throws Exception {
		super();
		this.locationVon = locationVon;
		this.locationBis = locationBis;
		getJsonDataFromGoogle();
	}

	private void getJsonDataFromGoogle() throws Exception {

		setJsonFile(HttpUtil.read(makeURL(), "proxy-sifi.rd.corpintra.net", "3128"));
		// setJsonFile(HttpUtil.read(makeURL(), null, null));

		JsonParser parser = new JsonParser();
		JsonObject rootObejct = parser.parse(getJsonFile()).getAsJsonObject();
		JsonElement projectElement = rootObejct.get("routes");

		Gson gson = new Gson();
		List<Route> projectList = new ArrayList<Route>();

		// Check if "project" element is an array or an object and parse
		// accordingly...
		if (projectElement.isJsonObject()) {
			// The returned list has only 1 element
			Route route = gson.fromJson(projectElement, Route.class);
			projectList.add(route);
		} else if (projectElement.isJsonArray()) {
			// The returned list has >1 elements
			Type projectListType = new TypeToken<List<Route>>() {
			}.getType();
			projectList = gson.fromJson(projectElement, projectListType);
		}

		for (Route r1 : projectList) {
			System.out.println("Route " + r1.getSummary());
			int dur = 0;
			for (Leg leg : r1.getLegs()) {
				List<Step> steps = leg.getSteps();
				for (Step step : steps) {
					dur += step.getDuration().getValue();
				}
				System.out.println("Duration : " + dur / 60.0);
				dur = 0;
			}

		}

	}

	private String makeURL() throws Exception {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("http://maps.googleapis.com/maps/api/directions/json");
		localStringBuilder.append("?origin=");
		localStringBuilder.append(URLEncoder.encode(getLocationVon(), "UTF-8"));
		localStringBuilder.append("&destination=");
		localStringBuilder.append(URLEncoder.encode(getLocationBis(), "UTF-8"));
		localStringBuilder.append("&sensor=false&mode=driving&alternatives=true");
		return localStringBuilder.toString();
	}

	private void parseDuration() {

	}

	protected String getJsonFile() {
		return jsonFile;
	}

	protected void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	protected String getLocationVon() {
		return locationVon;
	}

	protected void setLocationVon(String locationVon) {
		this.locationVon = locationVon;
	}

	protected String getLocationBis() {
		return locationBis;
	}

	protected void setLocationBis(String locationBis) {
		this.locationBis = locationBis;
	}

	protected int getDuration() {
		return duration;
	}

	protected void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public boolean schedule() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shoudAlertMe() {
		// TODO Auto-generated method stub
		return false;
	}

}
