package com.rarc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class Test  {
    
    @GET
    @Produces({ MediaType.TEXT_HTML, MediaType.TEXT_PLAIN })
    public String noRoute() {
        return "NAH MAN";
    }

    @GET
    @Path("/ve")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }
}