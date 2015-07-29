package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


@Path("/MyRESTApplication")
public class HelloWorldResource {

	
	
//////////////////////***************************************************************//////////////////////////////////////////////////////////////////////////
/////////////////////*///////////////////@BeanParam Example///////////////////////////////////////////////////
@GET()
@Produces(MediaType.APPLICATION_JSON)
@Path("/employeedetails")
public String postDetailsBean() throws JsonGenerationException, JsonMappingException, IOException{
	ObjectMapper mapper = new ObjectMapper();
	Map <String,String>empMap=new HashMap<String,String>();
	empMap.put("name", "abc");
	empMap.put("age", "30");
	return mapper.writeValueAsString(empMap);
}	
}