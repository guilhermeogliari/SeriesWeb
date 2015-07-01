package com.seriesweb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Stateless
@Path("/files")
public class FileUpload {
	
	@POST  
    @Path("/upload")  
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
	public Response uploadFile(
	        @FormDataParam("file") InputStream uploadedInputStream,
	        @FormDataParam("file") FormDataContentDisposition fileDetail) {

		
	    // Path format //10.217.14.97/Installables/uploaded/
	    System.out.println("path::"+System.getProperty("com.sun.aas.instanceRoot"));
	    String uploadedFileLocation = System.getProperty("com.sun.aas.instanceRoot") + "/eclipseApps/SeriesWeb/images/upload/" + fileDetail.getFileName();

	    try {
	        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        out = new FileOutputStream(new File(uploadedFileLocation));
	        while ((read = uploadedInputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String output = "File uploaded to : " + uploadedFileLocation;

	    return Response.status(200).entity(output).build();

	}
	
}
