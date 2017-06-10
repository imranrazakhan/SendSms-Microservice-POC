package org.restcomm.connect.http;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("/Accounts/{accountSid}/SMS/Messages")
public final class SmsMessages  {
    public SmsMessages() {
        super();
    }

    @GET
    public Response getSmsMessages(@PathParam("accountSid") final String accountSid, @Context UriInfo info) {
        return null;
    }

    @POST
    @Consumes({"application/x-www-form-urlencoded", "application/json"})  //The resource method invoked is determined by the HTTP Content-Type header of the request.
    public Response putSmsMessage(@PathParam("accountSid") final String accountSid, final MultivaluedMap<String, String> data, @HeaderParam("Content-Type") MediaType contentType) {
        return null;
    }
}