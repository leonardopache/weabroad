package com.pache.resource;

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
		return new HealthResponse(Status.OK, ""); 
	}
}
