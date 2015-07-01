package com.seriesweb.controller;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.seriesweb.model.Genre;

@Stateless
@Path("/Genre")
public class GenreController {

	@GET
	@Produces("application/json;charset=UTF-8")
	public Genre[] findAll(){
		return Genre.values();
	}
	
}
