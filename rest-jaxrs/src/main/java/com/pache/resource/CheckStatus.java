package com.pache.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.pache.resource.response.HealthResponse;

/**
 * @author lpache
 */
@Path("/")
public class CheckStatus {

	@GET
	@Path("check")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public HealthResponse status() {
		Properties properties = new Properties();
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
			properties.load(is);
		} catch (IOException e) {
			//TODO CHANGE THIS LOAD PROPERTIES TO SOMETHING BETTER
		}
		return new HealthResponse(Status.OK, properties.getProperty("app.version")); 
	}
}
