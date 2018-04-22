package com.pache.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pache.quartz.job.ManagerJobCountDays;
import com.pache.resource.exception.ApplicationResponseError;
import com.pache.utils.ManagerControls;

@Path("/job")
public class JobController {
	
	@POST
	@Path("start")
	@Produces({MediaType.APPLICATION_JSON})
	public Response start() throws ApplicationResponseError {
		ManagerJobCountDays.jobCountDays(ManagerControls.START);
		return Response.ok().build();
	}
	
	@POST
	@Path("stop")
	@Produces({MediaType.APPLICATION_JSON})
	public Response stop() throws ApplicationResponseError {
		ManagerJobCountDays.jobCountDays(ManagerControls.STOP);
		return Response.status(Status.OK).build();
	}

	@POST
	@Path("pause")
	@Produces({MediaType.APPLICATION_JSON})
	public Response pause() throws ApplicationResponseError {
		ManagerJobCountDays.jobCountDays(ManagerControls.PAUSE);
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("resume")
	@Produces({MediaType.APPLICATION_JSON})
	public Response resume() throws ApplicationResponseError {
		ManagerJobCountDays.jobCountDays(ManagerControls.RESUME);
		return Response.status(Status.OK).build();
	}
}
