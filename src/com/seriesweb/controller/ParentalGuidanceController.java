package com.seriesweb.controller;

import javax.ejb.Stateful;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.seriesweb.model.ParentalGuidance;


@Stateful
@Path("/ParentalGuidance")
public class ParentalGuidanceController {

	@GET
	@Produces("application/json;charset=UTF-8")
	public ParentalGuidance[] findAll(){
		return ParentalGuidance.values();
	}
	
}
