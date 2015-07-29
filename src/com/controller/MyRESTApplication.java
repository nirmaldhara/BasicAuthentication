package com.controller;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class MyRESTApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		System.out.println("Inside the controller");
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(HelloWorldResource.class);
		
		return classes;
	}
}