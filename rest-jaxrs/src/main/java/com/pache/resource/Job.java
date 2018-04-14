package com.pache.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pache.resource.exception.ApplicationResponseError;
import com.pache.service.ManagerJobs;

@Path("/job")
public class Job {
	
	@POST
	@Path("start")
	@Produces({MediaType.APPLICATION_JSON})
	public Response start() throws ApplicationResponseError {
		ManagerJobs.jobCountDays(ManagerControls.START);
		return Response.ok().build();
	}
	
	@POST
	@Path("stop")
	@Produces({MediaType.APPLICATION_JSON})
	public Response stop() throws ApplicationResponseError {
		ManagerJobs.jobCountDays(ManagerControls.STOP);
		return Response.status(Status.OK).build();
	}

	@POST
	@Path("pause")
	@Produces({MediaType.APPLICATION_JSON})
	public Response pause() throws ApplicationResponseError {
		ManagerJobs.jobCountDays(ManagerControls.PAUSE);
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("resume")
	@Produces({MediaType.APPLICATION_JSON})
	public Response resume() throws ApplicationResponseError {
		ManagerJobs.jobCountDays(ManagerControls.RESUME);
		return Response.status(Status.OK).build();
	}
}
