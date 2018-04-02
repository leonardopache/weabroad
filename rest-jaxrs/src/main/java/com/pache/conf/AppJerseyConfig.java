package com.pache.conf;

import org.glassfish.jersey.server.ResourceConfig;

public class AppJerseyConfig extends ResourceConfig{
	
	public AppJerseyConfig() {
		packages("com.pache.resource");
	}

}
