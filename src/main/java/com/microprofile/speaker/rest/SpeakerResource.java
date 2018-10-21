package com.microprofile.speaker.rest;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/speakers")
public class SpeakerResource {

    @Inject
    SpeakerService service;

    @GET
    @Produces("application/json")
    @Counted(name = "getAll", absolute = true, monotonic = true, description = "Number the times requested")
    @Operation(summary = "Get all speakers")
    @APIResponse(responseCode = "200",
            description = "List of speakers",
            content = @Content(schema = @Schema(implementation = Speaker.class)))
    public Response getAll() {
        return Response.ok(service.getSpeakers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Operation(summary = "Get a speaker by id")
    @APIResponse(responseCode = "200",
            description = "Speaker data",
            content = @Content(schema = @Schema(implementation = Speaker.class)))
    public Response getById(@PathParam("id") Integer id) {
        Optional<Speaker> speaker = service.getById(id);
        if (speaker.isPresent()) {
            return Response.ok(speaker.get()).build();
        } else {
            return Response.status(404).build();
        }
    }

}