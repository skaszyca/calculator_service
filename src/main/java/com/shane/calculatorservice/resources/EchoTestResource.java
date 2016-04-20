package com.shane.calculatorservice.resources;

import com.shane.calculatorservice.core.TestMessage;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/echotest")
@Produces(MediaType.APPLICATION_JSON)
public class EchoTestResource {
    private final String message;
    private final AtomicLong counter;

    public EchoTestResource(String message) {
        this.message = message;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public TestMessage echoTest() {
        return new TestMessage(counter.incrementAndGet(), message);
    }
	
}