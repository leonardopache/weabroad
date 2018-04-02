package com.pache.resource.response;

import javax.ws.rs.core.Response.Status;

public class HealthResponse {

	private Status status;
	private String version;
	private Object properties;
	private HealthResponse[] dependencies;

	public HealthResponse(Status status, String version) {
		this.status = status;
		this.version = version;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Object getProperties() {
		return properties;
	}

	public void setProperties(Object properties) {
		this.properties = properties;
	}

	public HealthResponse[] getDependencies() {
		return dependencies;
	}

	public void setDependencies(HealthResponse[] dependencies) {
		this.dependencies = dependencies;
	}

}
